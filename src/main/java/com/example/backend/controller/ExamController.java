package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.request.ExamCreationRequest;
import com.example.backend.dto.request.ExamUpdateRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.model.Exam;
import com.example.backend.service.ExamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

@RequestMapping(path = "/api/auth")
public class ExamController {
        @Autowired
        ExamService examService;

        @GetMapping("/exam-list")
        ApiResponse<List<Exam>> examList() {
                return ApiResponse.<List<Exam>>builder()
                                .data(examService.getExams()).code(200)
                                .build();
        }

        @PostMapping("/create-exam")
        ApiResponse<List<Exam>> createExam(@RequestBody List<ExamCreationRequest> request) {
                return ApiResponse.<List<Exam>>builder()
                                .data(examService.addExams(request)).code(200)
                                .build();
        }

        @PutMapping("/update-exam/{id}")
        ApiResponse<Exam> examUpdate(@PathVariable String id,
                        @RequestBody ExamUpdateRequest request) {
                return ApiResponse.<Exam>builder()
                                .data(examService.updateExam(id, request)).code(200)
                                .build();
        }

        @GetMapping("/exam-detail/{id}")
        ApiResponse<Exam> examDetail(@PathVariable String id) {
                return ApiResponse.<Exam>builder()
                                .data(examService.examById(id)).code(200)
                                .build();
        }

        @DeleteMapping("/exam-delete/{id}")
        ApiResponse<Void> examDelete(@PathVariable String id) {
                examService.deleteExam(id);
                return ApiResponse.<Void>builder().code(200).message("User has been deleted")
                    .build();
        }

}
