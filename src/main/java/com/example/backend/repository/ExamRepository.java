package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, String> {

}
