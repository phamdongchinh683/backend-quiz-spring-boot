package com.example.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.dto.request.role.RoleCreationRequest;
import com.example.backend.dto.response.RoleResponse;
import com.example.backend.mapper.RoleMapper;
import com.example.backend.repository.RoleRepository;

@Service
public class RoleService {
 @Autowired
 RoleRepository roleRepository;
 @Autowired
 RoleMapper roleMapper;

 public RoleResponse create(RoleCreationRequest request) {
  var role = roleMapper.insertRole(request);
  role = roleRepository.save(role);
  return roleMapper.toRoleResponse(role);
 }

 public List<RoleResponse> getRoles() {
  return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
 }

 public void delete(String id) {
  roleRepository.deleteById(id);
 }
}