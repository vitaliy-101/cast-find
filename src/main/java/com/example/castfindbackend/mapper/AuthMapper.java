package com.example.castfindbackend.mapper;

import com.example.castfindbackend.dto.auth.AuthRequest;
import com.example.castfindbackend.dto.auth.RegisterRequest;
import com.example.castfindbackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper {

    @Mapping(target = "password", qualifiedByName = "getEncodedPassword", source = "password")
    User fromAuthRequestToUser(AuthRequest authRequest);

    @Mapping(target = "password", qualifiedByName = "getEncodedPassword", source = "password")
    User fromRegisterRequestToUser(RegisterRequest registerRequest);

    @Named("getEncodedPassword")
    default String getEncodedPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
