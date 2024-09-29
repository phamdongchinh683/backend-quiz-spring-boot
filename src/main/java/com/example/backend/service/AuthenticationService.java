package com.example.backend.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.dto.request.AuthenticationRequest;
import com.example.backend.dto.request.IntrospectRequest;
import com.example.backend.dto.response.AuthenticationResponse;
import com.example.backend.dto.response.IntrospectResponse;
import com.example.backend.exception.AppException;
import com.example.backend.exception.ErrorCode;
import com.example.backend.model.User;
import com.example.backend.repository.InvalidatedTokenRepository;
import com.example.backend.repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class AuthenticationService {
        @Autowired
        UserRepository userRepository;
        @Autowired
        InvalidatedTokenRepository invalidatedTokenRepository;

        @NonFinal
        @Value("${jwt.signer-key}")
        protected String signerKey;

        @NonFinal
        @Value("${jwt.valid-duration}")
        protected long validDuration;

        @NonFinal
        @Value("${jwt.refreshable-duration}")
        protected long refreshableDuration;

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

                var user = userRepository.findByUsername(request.getUsername())
                                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

                boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

                if (!authenticated) {
                        throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
                }
                var token = generateToken(user);
                return AuthenticationResponse.builder().token(token).build();

        }

        private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
                JWSVerifier verifier = new MACVerifier(signerKey.getBytes());

                SignedJWT signedJWT = SignedJWT.parse(token);
                Date expiryTime = (isRefresh)
                                ? new Date(signedJWT
                                                .getJWTClaimsSet()
                                                .getIssueTime()
                                                .toInstant()
                                                .plus(refreshableDuration, ChronoUnit.SECONDS)
                                                .toEpochMilli())
                                : signedJWT.getJWTClaimsSet().getExpirationTime();

                var verified = signedJWT.verify(verifier);

                if (!(verified && expiryTime.after(new Date())))
                        throw new AppException(ErrorCode.UNAUTHENTICATED);

                if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
                        throw new AppException(ErrorCode.UNAUTHENTICATED);

                return signedJWT;
        }

        private String generateToken(User user) {
                JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

                String jwtId = UUID.randomUUID().toString();

                JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                                .subject(user.getUsername())
                                .issuer("quiz.com")
                                .issueTime(new Date())
                                .expirationTime(new Date(
                                                Instant.now().plus(validDuration, ChronoUnit.SECONDS).toEpochMilli()))
                                .jwtID(jwtId)
                                .build();

                Payload payload = new Payload(jwtClaimsSet.toJSONObject());

                JWSObject jwsObject = new JWSObject(header, payload);

                try {
                        jwsObject.sign(new MACSigner(signerKey.getBytes()));
                        return jwsObject.serialize();
                } catch (JOSEException e) {
                        throw new RuntimeException(e);
                }
        }

        public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
                var token = request.getToken();
                boolean isValid = true;

                try {
                        verifyToken(token, false);
                } catch (AppException e) {
                        isValid = false;

                }

                return IntrospectResponse.builder().valid(isValid).build();

        }
}
