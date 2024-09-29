package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.response.ApiResponse;
import com.example.backend.model.Exam;
import com.example.backend.service.ExamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/admin/manage-exam")

public class ExamController {
        @Autowired
        private ExamService examService;

        public ExamController(ExamService examService) {
                this.examService = examService;
        }

        @GetMapping("/exam-list")
        ApiResponse<List<Exam>> examList() {
                ApiResponse<List<Exam>> apiResponse = new ApiResponse<>();
                List<Exam> exams = examService.getExams();
                apiResponse.setCode(200);
                apiResponse.setData(exams);
                return apiResponse;
        }

        @PostMapping("/create-exam")
        ApiResponse<List<Exam>> createExam(@RequestBody @Valid List<Exam> request) {
                ApiResponse<List<Exam>> apiResponse = new ApiResponse<>();
                var saveExams = examService.addExam(request);
                apiResponse.setCode(200);
                apiResponse.setData(saveExams);
                return apiResponse;
        }

        // @PutMapping("/update/{id}")
        // UserResponse updateExam(@PathVariable("id") String id, @RequestBody
        // UserUpdate request) {
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
