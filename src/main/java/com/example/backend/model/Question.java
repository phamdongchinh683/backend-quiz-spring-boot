package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

@FieldDefaults(level = AccessLevel.PRIVATE)

@Table(name = "questions")
public class Question {
 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 String id;

 @Column(name = "question_content")
 String questionContent;

 @OneToOne(fetch = FetchType.EAGER)
 @JoinColumn(name = "category_question", nullable = false)
 Categories categoryQuestion;

 @ManyToOne(fetch = FetchType.EAGER)
 @JoinColumn(name = "exam_id", nullable = false)
 Exam examId;

 @Column(name = "answer_list")
 String answerList;

 @Column(name = "correct_answer")
 String correctAnswer;
}
