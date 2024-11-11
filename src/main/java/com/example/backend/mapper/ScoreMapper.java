package com.example.backend.mapper;

import org.mapstruct.Mapper;

import com.example.backend.dto.request.ExamTotalScore;
import com.example.backend.model.Score;

@Mapper(componentModel = "spring")
public interface ScoreMapper {
 Score saveScore(ExamTotalScore request);
}
