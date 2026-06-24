package com.niladri.userservice.service;

import com.niladri.userservice.dto.request.LoginRequest;
import com.niladri.userservice.dto.request.SignupRequest;
import com.niladri.userservice.dto.response.AuthResponse;

public interface IAuthService {
    AuthResponse signup(SignupRequest signupRequest);

    AuthResponse login(LoginRequest loginRequest);
}
