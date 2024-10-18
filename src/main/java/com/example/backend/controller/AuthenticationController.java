package com.example.backend.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.request.admin.UserCreationRequest;
import com.example.backend.dto.request.auth.AuthenticationRequest;
import com.example.backend.dto.request.jwt.TokenRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.AuthenticationResponse;
import com.example.backend.dto.response.TokenResponse;
import com.example.backend.service.AuthenticationService;
import com.example.backend.service.UserService;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class AuthenticationController {
  @Autowired
  AuthenticationService authenticationService;
  @Autowired
  UserService userService;

  @PostMapping("/login")
  ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    var result = authenticationService.authenticate(request);
    return ApiResponse.<AuthenticationResponse>builder()
        .data(result).code(200).build();
  }

  @PostMapping("/check-token")
  ApiResponse<TokenResponse> checkValidToken(@RequestBody TokenRequest request)
      throws ParseException, JOSEException {
    var result = authenticationService.introspect(request);
    return ApiResponse.<TokenResponse>builder().data(result).build();
  }

  @PostMapping("/signup")
  ApiResponse signUp(@RequestBody UserCreationRequest request) {
    userService.createUser(request);
    return ApiResponse.builder().message("Sign up successfully").code(200).build();
  }

}
