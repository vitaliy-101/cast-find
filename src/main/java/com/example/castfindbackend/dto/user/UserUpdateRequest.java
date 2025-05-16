package com.example.castfindbackend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateRequest {
    private String nickname;
    private String number;
    private String description;
    private List<String> areas;
    private List<String> contacts;
    private Long organisationId;
}
