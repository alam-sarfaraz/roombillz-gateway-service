package com.inn.controller;

import com.inn.dto.LoginRequest;
import com.inn.dto.TokenResponse;
import com.inn.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request){

        String token = authService.login(request);

        return new TokenResponse(token);
    }
}