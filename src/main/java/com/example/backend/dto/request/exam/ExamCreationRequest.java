package com.example.backend.dto.request.exam;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ExamCreationRequest {
        @Column(name = "name")
        String nameExam;
        @Column(name = "language_id")
        String languageId;
        @Column(name = "time_allowed")
        String timeAllowed;
        @Column(name = "question_quantity")
        String questionQuantity;
        @Column(name = "audio")
        String audio;
}