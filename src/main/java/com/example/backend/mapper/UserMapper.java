package com.example.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.backend.dto.request.UserCreation;
import com.example.backend.dto.request.UserUpdate;
import com.example.backend.dto.response.UserResponse;
import com.example.backend.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
        User toUser(UserCreation request);

        void update(@MappingTarget User user, UserUpdate request);

        UserResponse toUserResponse(User user);

}
