package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.request.QuestionCreationRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.model.Question;
import com.example.backend.service.ExamService;
import com.example.backend.service.QuestionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/v1/admin/manage-question")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class QuestionController {
  @Autowired
  QuestionService questionService;
  @Autowired
  ExamService examService;

  @GetMapping("/question-list")
  public List<Question> getQuestions() {
    return questionService.getQuestions();
  }

  @PostMapping("/add-question")
  ApiResponse<List<Question>> addQuestions(@RequestBody List<QuestionCreationRequest> requests) {
    return ApiResponse.<List<Question>>builder()
        .data(questionService.insertQuestion(requests)).code(200)
        .build();
  }

}
