package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.model.Score;

@Repository

public interface ScoreRepository extends JpaRepository<Score, String> {

}
