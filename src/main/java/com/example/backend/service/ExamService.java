package com.example.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.model.Exam;
import com.example.backend.repository.ExamRepository;

@Service
public class ExamService {

        @Autowired
        private ExamRepository examRepository;

        public List<Exam> getExams() {
                return examRepository.findAll();
        }

        @Transactional

        public List<Exam> addExam(List<Exam> request) {
                List<Exam> results = examRepository.saveAll(request);
                return results;
        }

        public Exam updateExam(String id, Exam request) {
                Exam exam = examRepository.findById(id).orElseThrow(() -> new RuntimeException("Exam not found"));
                return examRepository.save(exam);
        }

        public void deleteExam(String id) {
                examRepository.deleteById(id);
        }

}
