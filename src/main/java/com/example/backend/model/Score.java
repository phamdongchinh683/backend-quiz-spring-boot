package com.example.backend.model;

import java.util.Date;

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
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scores")

public class Score {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   String id;
   @Column(name = "score")
   float totalScore;
   @Column(name = "exam_id")
   String examId;
   @Column(name = "user_id")
   String userId;
   @Column(name = "time")
   String time;
   @Column(name = "exam_date")
   Date date;
}
