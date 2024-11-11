package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "exams")
public class Exam {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        String id;
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
