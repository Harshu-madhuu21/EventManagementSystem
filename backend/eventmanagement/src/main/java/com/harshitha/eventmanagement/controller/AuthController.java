package com.harshitha.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.harshitha.eventmanagement.dto.AuthResponse;
import com.harshitha.eventmanagement.dto.LoginRequest;
import com.harshitha.eventmanagement.dto.RegisterRequest;
import com.harshitha.eventmanagement.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {

        System.out.println("REGISTER API HIT");

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        System.out.println("Login API called");

        return authService.login(request);
    }
}