package com.niladri.userservice.service;

import com.niladri.userservice.dto.request.LoginRequest;
import com.niladri.userservice.dto.request.SignupRequest;
import com.niladri.userservice.dto.response.AuthResponse;
import com.niladri.userservice.dto.response.RefreshResponse;
import com.niladri.userservice.dto.response.SignupResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IAuthService {
    SignupResponse signup(SignupRequest signupRequest);

    AuthResponse login(LoginRequest loginRequest);

    RefreshResponse refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
