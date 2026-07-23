package com.harshitha.eventmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harshitha.eventmanagement.dto.AuthResponse;
import com.harshitha.eventmanagement.dto.LoginRequest;
import com.harshitha.eventmanagement.dto.RegisterRequest;
import com.harshitha.eventmanagement.entity.User;
import com.harshitha.eventmanagement.repository.UserRepository;
import com.harshitha.eventmanagement.security.JwtService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token, "User Registered Successfully");
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Email"));

        // Debugging
        System.out.println("Entered Password : " + request.getPassword());
        System.out.println("DB Password      : " + user.getPassword());

        boolean match = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword());

        System.out.println("Password Match : " + match);

        // If password is wrong
        if (!match) {
            throw new RuntimeException("Invalid Password");
        }

        // Generate JWT Token
        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token, "Login Successful");
    }
}