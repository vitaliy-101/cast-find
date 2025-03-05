package com.example.castfindbackend.service;

import com.example.castfindbackend.dto.role.RoleSelectionRequest;
import com.example.castfindbackend.dto.role.RoleSelectionResponse;
import com.example.castfindbackend.entity.User;
import com.example.castfindbackend.exceptions.NotFoundByIdException;
import com.example.castfindbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public RoleSelectionResponse selectRole(RoleSelectionRequest request) {
        try {
            var user = findById(request.getUserId());
            user.setRole(request.getRole());
            userRepository.save(user);
            return new RoleSelectionResponse(true, "");
        } catch (Exception e) {
            return new RoleSelectionResponse(false, e.getMessage());
        }
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(User.class, id));
    }
}
