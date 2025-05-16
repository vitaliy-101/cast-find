package com.example.castfindbackend.service;

import com.example.castfindbackend.dto.portfolio.PortfolioFolderCreateRequest;
import com.example.castfindbackend.dto.portfolio.PortfolioFolderCreateResponse;
import com.example.castfindbackend.dto.portfolio.PortfolioInfo;
import com.example.castfindbackend.repository.PortfolioFolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.castfindbackend.constant.PhotoTypes.PORTFOLIO_PHOTO_TYPE;
import static com.example.castfindbackend.mapper.CustomPortfolioMapper.*;

@Service
@RequiredArgsConstructor
public class PortfolioService {
    private final PortfolioFolderRepository repository;
    private final UserService userService;
    private final PhotoService photoService;

    public PortfolioFolderCreateResponse create(PortfolioFolderCreateRequest request, Long userId) {
        var entity = repository.save(toEntity(request, userService.findById(userId)));
        return toCreateResponse(entity);
    }

    public List<PortfolioInfo> getUserPortfolios(Long userId) {
        var entities = repository.findPortfolioFoldersByUserId(userId);
        if (entities == null || entities.isEmpty()) {
            return new ArrayList<>();
        }
        return entities.stream()
                .map(entity -> toInfo(entity, photoService.findAvatar(entity.getId(), PORTFOLIO_PHOTO_TYPE)))
                .toList();
    }




}
