package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.request.exam.ExamCreationRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.model.Exam;
import com.example.backend.service.ExamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

@RequestMapping(path = "/api/v1/admin/manage-exam")

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

        // @PutMapping("/update/{id}")
        // ApiResponse<String> updateExam(@PathVariable("id") String id, @RequestBody
        // ExamUpdateRequest request) {
        // return examService.updateExam(id, request);
        // }

        // @DeleteMapping("/delete/{id}")
        // ApiResponse<Exam> deleteExam(@PathVariable("id") String id) {
        // ApiResponse<Exam> apiResponse = new ApiResponse<>();
        // examService.deleteExam(id);
        // apiResponse.setMessage("User has been deleted");
        // return apiResponse;
        // }
}
