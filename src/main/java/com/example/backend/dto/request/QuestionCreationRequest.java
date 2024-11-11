package com.example.backend.dto.request;

import com.example.backend.model.Categories;
import com.example.backend.model.Exam;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class QuestionCreationRequest {
 String questionContent;
 Categories categoryQuestion;
 Exam examId;
 String answerList;
 String correctAnswer;
}