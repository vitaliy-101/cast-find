package com.example.castfindbackend.mapper;

import com.example.castfindbackend.dto.user.UserUpdateRequest;
import com.example.castfindbackend.entity.Organisation;
import com.example.castfindbackend.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomUserMapper {

    public static User toUpdateEntity(User user,
                                      UserUpdateRequest request,
                                      Organisation organisation) {
        user.setNickname(request.getNickname());
        user.setNumber(request.getNumber());
        user.setDescription(request.getDescription());
        user.setArea(request.getAreas());
        user.setContacts(request.getContacts());
        user.setOrganisation(organisation);
        return user;
    }
}
