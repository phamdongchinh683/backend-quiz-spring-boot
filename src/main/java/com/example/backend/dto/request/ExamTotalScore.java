package com.example.backend.dto.request;

import java.util.Date;

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
public class ExamTotalScore {
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
