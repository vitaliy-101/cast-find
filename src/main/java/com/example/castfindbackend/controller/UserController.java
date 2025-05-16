package com.example.castfindbackend.controller;

import com.example.castfindbackend.dto.role.RoleSelectionRequest;
import com.example.castfindbackend.dto.role.RoleSelectionResponse;
import com.example.castfindbackend.dto.user.UserInfoResponse;
import com.example.castfindbackend.dto.user.UserUpdateRequest;
import com.example.castfindbackend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.castfindbackend.utils.UserUtils.extractUserId;

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

    @PostMapping("/organisation/{id}")
    public void chooseOrganisation(@PathVariable("id") Long organisationId) {
        var userId = extractUserId();
        userService.setOrganisation(userId, organisationId);
    }

    @GetMapping("/info")
    public UserInfoResponse getUserInfo() {
        var userId = extractUserId();
        return userService.getUserInfo(userId);
    }

    @PutMapping
    public UserInfoResponse update(@RequestBody UserUpdateRequest request) {
        var userId = extractUserId();
        return userService.updateUser(userId, request);
    }


}
