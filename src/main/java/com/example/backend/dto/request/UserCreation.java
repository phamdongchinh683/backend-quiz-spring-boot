package com.example.backend.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
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
public class UserCreation {
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
        @Size(min = 6, message = "USERNAME_INVALID")
        @Column(name = "username")
        String username;
        @Size(min = 8, message = "INVALID_PASSWORD")
        @Column(name = "password")
        String password;
        @Column(name = "email")
        String email;
        @Column(name = "phone_number")
        String phoneNumber;
        @Column(name = "role_id")
        String roleId;
        @Column(name = "image")
        String image;
        @Column(name = "gender")
        String gender;
        @Column(name = "birth_day")
        String birthDay;

}