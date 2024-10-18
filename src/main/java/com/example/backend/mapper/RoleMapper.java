package com.example.backend.mapper;

import org.mapstruct.Mapper;

import com.example.backend.dto.request.role.RoleCreationRequest;
import com.example.backend.dto.response.RoleResponse;
import com.example.backend.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
 Role insertRole(RoleCreationRequest request);

 RoleResponse toRoleResponse(Role param);
}