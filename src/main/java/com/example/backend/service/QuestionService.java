package com.example.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.dto.request.QuestionCreationRequest;
import com.example.backend.dto.response.QuestionResponse;
import com.example.backend.mapper.QuestionMapper;
import com.example.backend.model.Question;
import com.example.backend.repository.QuestionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
 @Autowired
 QuestionRepository questionRepository;
 @Autowired
 ExamService examService;
 @Autowired
 QuestionMapper questionMapper;

 public List<Question> getQuestions() {
  return questionRepository.findAll();
 }

 public List<QuestionResponse> getQuestionsByExamId(String examId) {
  return questionRepository.findQuestionsByExamId(examId);
 }

 @Transactional
 public List<Question> insertQuestion(List<QuestionCreationRequest> requests) {
  List<Question> saveQuestions = new ArrayList<>();

  for (QuestionCreationRequest request : requests) {
   Question question = questionMapper.toEntity(request);
   saveQuestions.add(question);
  }
  return questionRepository.saveAll(saveQuestions);
 }

}
