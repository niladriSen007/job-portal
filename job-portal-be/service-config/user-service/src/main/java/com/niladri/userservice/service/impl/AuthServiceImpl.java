package com.niladri.userservice.service.impl;

import com.niladri.domain.UserRole;
import com.niladri.domain.UserStatus;
import com.niladri.userservice.dto.request.LoginRequest;
import com.niladri.userservice.dto.request.SignupRequest;
import com.niladri.userservice.dto.response.AuthResponse;
import com.niladri.userservice.entity.UserEntity;
import com.niladri.userservice.exceptions.CannotRegisterAsAdminException;
import com.niladri.userservice.exceptions.UserAlreadyExistsException;
import com.niladri.userservice.mapper.Mapper;
import com.niladri.userservice.repository.AuthRepository;
import com.niladri.userservice.service.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements IAuthService {

    private final AuthRepository authRepository;

    @Override
    public AuthResponse signup(SignupRequest signupRequest) {
        log.info("Signup request received - AuthServiceImpl");

        // Firstly we will have to check the user email already exists in the database or not
        Optional<UserEntity> userEntity = authRepository.findByEmail(signupRequest.getEmail());
        if (userEntity.isPresent()) {
            throw new UserAlreadyExistsException("User already exists for the user - {}" + signupRequest.getEmail());
        }

        // If user tries to signup as admin then we need to restrict them
        if (signupRequest.getRole().equals(UserRole.ROLE_ADMIN))
            throw new CannotRegisterAsAdminException("The role of the user has been set to the ADMIN");

        // Creating the UserEntity from the request payload
        UserEntity user = UserEntity.builder()
                .email(signupRequest.getEmail())
                .password(signupRequest.getPassword())
                .firstName(signupRequest.getFirstName())
                .lastName(signupRequest.getLastName())
                .phoneNumber(signupRequest.getPhoneNumber())
                .role(signupRequest.getRole())
                .status(UserStatus.ACTIVE)
                .lastLoggedInTime(LocalDateTime.now())
                .build();

        // Saving the user into the database
        UserEntity registeredUser = authRepository.save(user);

        // Mapping as per the AuthResponse
        AuthResponse authResponse = AuthResponse.builder()
                .title("Welcome to the Job Portal")
                .message("Registered successfully")
                .userResponse(Mapper.toUserResponse(registeredUser))
                .token("")
                .build();

        // Return the result
        return authResponse;
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }
}
