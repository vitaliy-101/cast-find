package com.example.castfindbackend.dto.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoleSelectionResponse {
    private boolean result;
    private String error;
}
