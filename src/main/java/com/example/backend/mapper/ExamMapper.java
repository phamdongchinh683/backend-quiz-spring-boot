package com.example.backend.mapper;

import org.mapstruct.Mapper;

import com.example.backend.dto.request.ExamCreationRequest;
import com.example.backend.model.Exam;

@Mapper(componentModel = "spring")

public interface ExamMapper {

 Exam toExam(ExamCreationRequest request);

}
