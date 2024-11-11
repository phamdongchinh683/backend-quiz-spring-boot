package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@JsonInclude(JsonInclude.Include.NON_NULL)

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Categories {
 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 String id;
 @Column(name = "point")
 String point;
 @Column(name = "name")
 String name;

}
