package com.example.castfindbackend.dto.user;

import com.example.castfindbackend.dto.organisations.OrganisationInfo;
import com.example.castfindbackend.dto.photo.PhotoData;
import com.example.castfindbackend.dto.photo.PhotoResponse;
import com.example.castfindbackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoResponse {
    private Long id;
    private String nickname;
    private String number;
    private Role role;
    private OrganisationInfo organisationInfo;
    private PhotoResponse photo;
}
