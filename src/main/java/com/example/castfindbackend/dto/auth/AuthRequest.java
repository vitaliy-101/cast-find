package com.example.castfindbackend.dto.auth;

import com.example.castfindbackend.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String nickname;
    private String password;
}
