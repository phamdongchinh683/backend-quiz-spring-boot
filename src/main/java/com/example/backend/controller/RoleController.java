package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.request.RoleCreationRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.RoleResponse;
import com.example.backend.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/v1/admin/manage-role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class RoleController {

 @Autowired
 RoleService roleService;

 @PostMapping("/create-role")
 ApiResponse<RoleResponse> create(@RequestBody RoleCreationRequest request) {
  return ApiResponse.<RoleResponse>builder()
    .data(roleService.create(request))
    .build();
 }

 @GetMapping("/role-list")
 ApiResponse<List<RoleResponse>> roleList() {
  ApiResponse<List<RoleResponse>> apiResponse = new ApiResponse<>();
  List<RoleResponse> roles = roleService.getRoles();
  apiResponse.setCode(200);
  apiResponse.setData(roles);
  return apiResponse;
 }

 @DeleteMapping("/role-delete/{id}")
 ApiResponse<Void> delete(@PathVariable String id) {
  roleService.delete(id);
  return ApiResponse.<Void>builder().build();
 }

}
