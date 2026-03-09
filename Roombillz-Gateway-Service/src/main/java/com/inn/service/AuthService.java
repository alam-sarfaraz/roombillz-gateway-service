package com.inn.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.client.RoomBillzClient;
import com.inn.dto.AuthResponse;
import com.inn.dto.LoginRequest;
import com.inn.security.JwtUtil;

@Service
public class AuthService {

	 @Autowired
	    private RoomBillzClient roomBillzClient;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(LoginRequest request){

        AuthResponse user = roomBillzClient.login(request).getBody();

        if(user == null || !user.getIsActive()){
            throw new RuntimeException("Invalid credentials");
        }

        List<String> roles = user.getRoles()
                .stream()
                .map(r -> r.getRoleName())
                .collect(Collectors.toList());

        return jwtUtil.generateToken(user.getUserName(), roles);
    }
}