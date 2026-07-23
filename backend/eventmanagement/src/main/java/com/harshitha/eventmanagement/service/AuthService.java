package com.harshitha.eventmanagement.service;

import com.harshitha.eventmanagement.dto.AuthResponse;
import com.harshitha.eventmanagement.dto.LoginRequest;
import com.harshitha.eventmanagement.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}