package com.example.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.dto.request.SubmitAnswerRequest;
import com.example.backend.dto.response.ScoreResponse;
import com.example.backend.model.Question;
import com.example.backend.repository.QuestionRepository;
import com.example.backend.repository.ScoreRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScoreService {

  @Autowired
  QuestionRepository questionRepository;
  @Autowired
  ScoreRepository scoreRepository;

  public ScoreResponse totalScore(List<SubmitAnswerRequest> requests) {
    List<Question> questions = questionRepository.findAll();

    double totalScore = 0.0;

    for (SubmitAnswerRequest request : requests) {
      Question question = questions.stream()
          .filter(q -> q.getId().equals(request.getId()))
          .findFirst()
          .orElse(null);

      if (question != null && question.getCorrectAnswer().equalsIgnoreCase(request.getAnswer())) {
        totalScore += 0.25;
      }
    }

    ScoreResponse response = new ScoreResponse();
    response.setTotalScore(totalScore);

    return response;
  }

}
