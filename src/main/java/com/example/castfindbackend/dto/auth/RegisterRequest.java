package com.example.castfindbackend.dto.auth;

import com.example.castfindbackend.entity.Role;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RegisterRequest extends AuthRequest {
    private Role role;
    private String number;
}
