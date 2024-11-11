package com.example.backend.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.request.AuthenticationRequest;
import com.example.backend.dto.request.SubmitAnswerRequest;
import com.example.backend.dto.request.TokenRequest;
import com.example.backend.dto.request.UserCreationRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.AuthenticationResponse;
import com.example.backend.dto.response.QuestionResponse;
import com.example.backend.dto.response.ScoreResponse;
import com.example.backend.dto.response.TokenResponse;
import com.example.backend.service.AuthenticationService;
import com.example.backend.service.QuestionService;
import com.example.backend.service.ScoreService;
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
  @Autowired
  QuestionService questionService;
  @Autowired
  ScoreService scoreService;

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

  @GetMapping("/question-exam/{id}")
  ApiResponse<List<QuestionResponse>> getQuestionByExamId(@PathVariable String id) {
    return ApiResponse.<List<QuestionResponse>>builder()
        .data(questionService.getQuestionsByExamId(id)).code(200)
        .build();
  }

  @PostMapping("/submit-answer/{examId}/{userId}")
  ApiResponse<ScoreResponse> submitAnswer(@RequestBody List<SubmitAnswerRequest> request,
      @PathVariable String examId,
      @PathVariable String userId) {
    return ApiResponse.<ScoreResponse>builder()
        .data(scoreService.totalScore(request))
        .code(200)
        .build();
  }

  @GetMapping("/exam-result")
  ApiResponse<ScoreResponse> examResult(@RequestBody List<SubmitAnswerRequest> request,
      @PathVariable String examId,
      @PathVariable String userId) {
    return ApiResponse.<ScoreResponse>builder()
        .data(scoreService.totalScore(request))
        .code(200)
        .build();
  }
}