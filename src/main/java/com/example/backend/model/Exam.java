package com.example.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "exams")
public class Exam {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        String id;
        @Column(name = "name")
        String name;
        @Column(name = "language_id")
        String languageId;
        @Column(name = "time_allowed")
        String timeAllowed;
        @Column(name = "question_quantity")
        String questionQuantity;
        @Column(name = "audio")
        String audio;

}
