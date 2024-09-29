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

import com.example.backend.dto.request.UserCreation;
import com.example.backend.dto.request.UserUpdate;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.UserResponse;
import com.example.backend.model.User;
import com.example.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/admin/manage-user")
public class UserController {

 @Autowired
 private UserService userService;

 public UserController(UserService userService) {
  this.userService = userService;
 }

 @GetMapping("/user-list")
 ApiResponse<List<User>> userList() {
  ApiResponse<List<User>> apiResponse = new ApiResponse<>();
  List<User> users = userService.getUsers();
  apiResponse.setCode(200);
  apiResponse.setData(users);
  return apiResponse;
 }

 @PostMapping("/create-user")
 ApiResponse<User> createUser(@RequestBody @Valid UserCreation request) {
  ApiResponse<User> apiResponse = new ApiResponse<>();
  apiResponse.setData(userService.createUser(request));
  return apiResponse;
 }

 @GetMapping("/user-info/{id}")
 UserResponse profile(@PathVariable("id") String id) {
  return userService.getUser(id);
 }

 @PutMapping("/user-update/{id}")
 UserResponse updateUser(@PathVariable("id") String id, @RequestBody UserUpdate request) {
  return userService.updateUser(id, request);
 }

 @DeleteMapping("/delete-user/{id}")
 ApiResponse<User> deleteUser(@PathVariable("id") String id) {
  ApiResponse<User> apiResponse = new ApiResponse<>();
  userService.deleteUser(id);
  apiResponse.setMessage("User has been deleted");
  return apiResponse;
 }
}