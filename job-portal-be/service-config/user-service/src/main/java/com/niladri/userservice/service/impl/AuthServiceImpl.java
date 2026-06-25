package com.niladri.userservice.service.impl;

import com.niladri.domain.UserRole;
import com.niladri.domain.UserStatus;
import com.niladri.userservice.dto.request.LoginRequest;
import com.niladri.userservice.dto.request.SignupRequest;
import com.niladri.userservice.dto.response.AuthResponse;
import com.niladri.userservice.entity.UserEntity;
import com.niladri.userservice.exceptions.CannotRegisterAsAdminException;
import com.niladri.userservice.exceptions.InvalidCredentialsException;
import com.niladri.userservice.exceptions.UserAlreadyExistsException;
import com.niladri.userservice.mapper.Mapper;
import com.niladri.userservice.repository.AuthRepository;
import com.niladri.userservice.security.AppUserDetailsService;
import com.niladri.userservice.security.JWTService;
import com.niladri.userservice.security.UserInfoService;
import com.niladri.userservice.service.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements IAuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AppUserDetailsService appUserDetailsService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signup(SignupRequest signupRequest) {
        log.info("Signup request received in service - AuthServiceImpl");

        // Firstly we will have to check the user email already exists in the database or not
        Optional<UserEntity> userEntity = authRepository.findByEmail(signupRequest.getEmail());
        if (userEntity.isPresent()) {
            throw new UserAlreadyExistsException("User already exists for the user - " + signupRequest.getEmail());
        }

        // Creating the UserEntity from the request payload
        UserEntity user = UserEntity.builder()
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .firstName(signupRequest.getFirstName())
                .lastName(signupRequest.getLastName())
                .phoneNumber(signupRequest.getPhoneNumber())
                .lastLoggedInTime(LocalDateTime.now())
                .build();

        // Saving the user into the database
        UserEntity registeredUser = authRepository.save(user);

        // Create the Authentication Object
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getPassword()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtService.generateAccessToken(authentication, user.getId());

        // Mapping as per the AuthResponse
        AuthResponse authResponse = AuthResponse.builder()
                .title("Welcome to the Job Portal")
                .message("Registered successfully")
                .userResponse(Mapper.toUserResponse(registeredUser))
                .token(accessToken)
                .build();

        // Return the result
        return authResponse;
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        log.info("Login request received in service - AuthServiceImpl");

        Authentication authentication = authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());

//        SecurityContextHolder.getContext().setAuthentication(authentication);


//        Optional<UserEntity> user = authRepository.findByEmail(loginRequest.getEmail());
        UserInfoService principal = (UserInfoService) authentication.getPrincipal();
        Optional<UserEntity> user = authRepository.findByEmail(principal.getUsername());
        String accessToken = jwtService.generateAccessToken(authentication, user.get().getId());
        user.get().setLastLoggedInTime(LocalDateTime.now());
        authRepository.save(user.get());

        return AuthResponse.builder()
                .title("Welcome to the Job Portal")
                .message("Logged in successfully")
                .userResponse(Mapper.toUserResponse(user.get()))
                .token(accessToken)
                .build();
    }

    private Authentication authenticateUser(String email, String password) {

        UserDetails userDetails = appUserDetailsService.loadUserByUsername(email);

        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found with username - " + email);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new InvalidCredentialsException("Invalid password");
        }

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

    }
}
