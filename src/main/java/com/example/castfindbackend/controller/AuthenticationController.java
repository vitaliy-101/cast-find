package com.example.castfindbackend.controller;

import com.example.castfindbackend.dto.auth.AuthRequest;
import com.example.castfindbackend.dto.auth.AuthenticationResponse;
import com.example.castfindbackend.dto.auth.RegisterRequest;
import com.example.castfindbackend.entity.User;
import com.example.castfindbackend.mapper.AuthMapper;
import com.example.castfindbackend.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Регистрация и логин")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final AuthMapper authMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(authMapper.fromRegisterRequestToUser(request)));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthRequest request) {
        var user = new User();
        user.setNickname(request.getNickname());
        user.setPassword(request.getPassword());
        return ResponseEntity.ok(authenticationService.authenticate(user));
    }

}