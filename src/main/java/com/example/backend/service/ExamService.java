package com.example.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.dto.request.exam.ExamCreationRequest;
import com.example.backend.dto.request.exam.ExamUpdateRequest;
import com.example.backend.mapper.ExamMapper;
import com.example.backend.model.Exam;
import com.example.backend.repository.ExamRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service

public class ExamService {

        @Autowired
        ExamRepository examRepository;
        @Autowired
        ExamMapper examMapper;

        public List<Exam> getExams() {
                return examRepository.findAll();
        }

        @Transactional

        public List<Exam> addExams(List<ExamCreationRequest> requests) {

                List<Exam> examsToSave = new ArrayList<>();

                for (ExamCreationRequest request : requests) {
                        Exam exam = examMapper.toExam(request);
                        examsToSave.add(exam);
                }
                List<Exam> saveExams = examRepository.saveAll(examsToSave);
                return saveExams;
        }

        public Exam updateExam(String id, ExamUpdateRequest request) {
                Exam exam = examRepository.findById(id).orElseThrow(() -> new RuntimeException("Exam not found"));
                return examRepository.save(exam);
        }

        public void deleteExam(String id) {
                examRepository.deleteById(id);
        }

}
