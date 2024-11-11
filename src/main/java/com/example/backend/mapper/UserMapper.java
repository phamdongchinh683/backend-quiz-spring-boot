package com.example.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.backend.dto.request.UserCreationRequest;
import com.example.backend.dto.request.UserUpdateRequest;
import com.example.backend.dto.response.UserResponse;
import com.example.backend.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
        User toUser(UserCreationRequest request);

        void update(@MappingTarget User user, UserUpdateRequest request);

        UserResponse toUserResponse(User user);

}
