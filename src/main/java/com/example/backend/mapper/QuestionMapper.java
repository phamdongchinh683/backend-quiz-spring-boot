package com.example.backend.mapper;

import org.mapstruct.Mapper;

import com.example.backend.dto.request.QuestionCreationRequest;
import com.example.backend.model.Question;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

 Question toEntity(QuestionCreationRequest request);

}
