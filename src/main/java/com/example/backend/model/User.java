package com.example.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)

@Table(name = "users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        String id;
        @Column(name = "first_name")
        String firstName;
        @Column(name = "last_name")
        String lastName;
        @Column(name = "age")
        int age;
        @Column(name = "city_name")
        String cityName;
        @Column(name = "address")
        String address;
        @Column(name = "username")
        String username;
        @Column(name = "password")
        String password;
        @Column(name = "email")
        String email;
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "role_id", nullable = false)
        Role role;
        @Column(name = "phone_number")
        String phoneNumber;
        @Column(name = "image")
        String image;
        @Column(name = "gender")
        String gender;
        @Column(name = "birth_day")
        String birthDay;
}
