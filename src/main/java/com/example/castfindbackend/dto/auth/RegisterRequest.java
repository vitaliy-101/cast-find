package com.example.castfindbackend.dto.auth;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RegisterRequest extends AuthRequest {
    private String phoneNumber;
}
