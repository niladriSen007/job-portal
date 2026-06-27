package com.niladri.userservice.service;

import java.util.List;

import com.niladri.dto.response.UserResponse;
import com.niladri.userservice.dto.request.UpdateRequest;

public interface IUserService {
    UserResponse getUserByEmail(String email);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(String email, UpdateRequest updateRequest);

    // Admin methods
    UserResponse suspendUser(Long userId);

    UserResponse activateUser(Long userId);

    UserResponse deleteUser(Long userId);
}
