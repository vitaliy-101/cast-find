package com.example.castfindbackend.dto.role;

import com.example.castfindbackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RoleSelectionRequest {
    private Role role;
    private Long userId;
}
