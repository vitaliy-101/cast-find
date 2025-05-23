package com.example.castfindbackend.dto.user;

import com.example.castfindbackend.dto.organisations.OrganisationInfo;
import com.example.castfindbackend.dto.photo.PhotoResponse;
import com.example.castfindbackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoResponse {
    private Long id;
    private String nickname;
    private String number;
    private Role role;
    private String description;
    private List<String> areas;
    private List<String> contacts;
    private OrganisationInfo organisationInfo;
    private PhotoResponse photo;
}
