package com.niladri.userservice.controller;

import com.niladri.dto.response.ApiResponse;
import com.niladri.userservice.dto.request.LoginRequest;
import com.niladri.userservice.dto.request.SignupRequest;
import com.niladri.userservice.dto.response.AuthResponse;
import com.niladri.userservice.service.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AuthResponse>> signup(@RequestBody @Valid SignupRequest signupRequest) {
        log.info("Signup request received in controller - AuthController");
        return ResponseEntity.ok(ApiResponse.success(authService.signup(signupRequest), 201));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody @Valid LoginRequest loginRequest) {
        log.info("Login request received in controller - AuthController");
        return ResponseEntity.ok(ApiResponse.success(authService.login(loginRequest)));
    }

    @GetMapping("/hi")
    public ResponseEntity<ApiResponse<String>> hi() {
        return ResponseEntity.ok(ApiResponse.success("Hi"));
    }
}
