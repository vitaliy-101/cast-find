package com.example.castfindbackend.mapper;

import com.example.castfindbackend.dto.user.UserInfoResponse;
import com.example.castfindbackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserInfoResponse toResponse(User user);
}
