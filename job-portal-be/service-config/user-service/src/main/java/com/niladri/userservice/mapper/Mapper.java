package com.niladri.userservice.mapper;

import com.niladri.dto.response.UserResponse;
import com.niladri.userservice.dto.request.SignupRequest;
import com.niladri.userservice.entity.UserEntity;

public class Mapper {

    public static UserEntity toUserEntity(SignupRequest request) {
        return UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public static UserResponse toUserResponse(UserEntity entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .profileImage(entity.getProfileImage())
                .role(entity.getRole())
                .userPermissions(entity.getPermissions())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .lastLoggedInTime(entity.getLastLoggedInTime())
                .build();
    }
}
