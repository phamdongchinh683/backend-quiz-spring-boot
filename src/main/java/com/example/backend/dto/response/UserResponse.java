package com.example.backend.dto.response;

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

public class UserResponse {
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
        @Column(name = "email")
        String email;
        @Column(name = "phone_number")
        String phoneNumber;
        @Column(name = "image")
        String image;
        @Column(name = "gender")
        String gender;
        @Column(name = "birth_day")
        String birthDay;
}
