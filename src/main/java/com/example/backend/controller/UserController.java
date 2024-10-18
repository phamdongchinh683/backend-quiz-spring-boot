package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.request.admin.UserCreationRequest;
import com.example.backend.dto.request.admin.UserUpdateRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.UserResponse;
import com.example.backend.model.User;
import com.example.backend.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/admin/manage-user")
@RequiredArgsConstructor

public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/user-list")
  ApiResponse<List<User>> userList() {
    return ApiResponse.<List<User>>builder()
        .data(userService.getUsers()).code(200)
        .build();

  }

  @PostMapping("/create-user")
  ApiResponse<List<User>> createUser(@RequestBody List<UserCreationRequest> requests) {
    return ApiResponse.<List<User>>builder()
        .data(userService.createUsers(requests)).code(200)
        .build();
  }

  @GetMapping("/user-info/{id}")
  ApiResponse<UserResponse> profile(@PathVariable("id") String id) {
    return ApiResponse.<UserResponse>builder()
        .data(userService.getUser(id)).code(200)
        .build();
  }

  @PutMapping("/user-update/{id}")
  ApiResponse<UserResponse> updateUser(@PathVariable("id") String id, @RequestBody UserUpdateRequest request) {
    return ApiResponse.<UserResponse>builder()
        .data(userService.updateUser(id, request)).code(200)
        .build();
  }

  @DeleteMapping("/delete-user/{id}")
  ApiResponse<Void> deleteUser(@PathVariable("id") String id) {
    userService.deleteUser(id);
    return ApiResponse.<Void>builder().code(200).message("User has been deleted")
        .build();
  }
}