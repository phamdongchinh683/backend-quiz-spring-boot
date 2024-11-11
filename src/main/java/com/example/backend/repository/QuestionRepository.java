package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.dto.response.QuestionResponse;
import com.example.backend.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {

  @Query("SELECT new com.example.backend.dto.response.QuestionResponse(q.questionContent, q.answerList, q.correctAnswer) "
      +
      "FROM Question q " +
      "JOIN q.categoryQuestion ctgr " +
      "JOIN q.examId exam " +
      "WHERE exam.id = :examId")
  List<QuestionResponse> findQuestionsByExamId(@Param("examId") String examId);

}
