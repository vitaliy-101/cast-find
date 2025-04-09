package com.example.castfindbackend.service;

import com.example.castfindbackend.dto.role.RoleSelectionRequest;
import com.example.castfindbackend.dto.role.RoleSelectionResponse;
import com.example.castfindbackend.dto.user.UserInfoResponse;
import com.example.castfindbackend.entity.User;
import com.example.castfindbackend.exceptions.NotFoundByIdException;
import com.example.castfindbackend.mapper.OrganisationMapper;
import com.example.castfindbackend.mapper.UserMapper;
import com.example.castfindbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final OrganisationService organisationService;
    private final OrganisationMapper organisationMapper;
    private final PhotoService photoService;

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

    public UserInfoResponse getUserInfo(Long id) {
        var user = findById(id);
        var userResponse = userMapper.toResponse(user);
        userResponse.setOrganisationInfo(organisationMapper.toInfo(user.getOrganisation()));
        userResponse.setPhoto(photoService.findAvatar(id, "USER"));
        return userResponse;
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(User.class, id));
    }

    @Modifying
    @Transactional
    public void setOrganisation(Long userId, Long organisationId) {
        var user = findById(userId);
        user.setOrganisation(organisationService.findById(organisationId));
        userRepository.save(user);
    }


}
