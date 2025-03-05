package com.example.castfindbackend.controller;

import com.example.castfindbackend.dto.role.RoleSelectionRequest;
import com.example.castfindbackend.dto.role.RoleSelectionResponse;
import com.example.castfindbackend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "Логика работы с пользователем")
public class UserController {
    private final UserService userService;

    @PostMapping("/role")
    public RoleSelectionResponse selectRole(@RequestBody RoleSelectionRequest request) {
        return userService.selectRole(request);
    }


}
