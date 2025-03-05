package com.example.castfindbackend.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String nickname;
    private String password;
}
