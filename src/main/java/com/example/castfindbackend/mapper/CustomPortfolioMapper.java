package com.example.castfindbackend.mapper;

import com.example.castfindbackend.dto.photo.PhotoResponse;
import com.example.castfindbackend.dto.portfolio.PortfolioFolderCreateRequest;
import com.example.castfindbackend.dto.portfolio.PortfolioFolderCreateResponse;
import com.example.castfindbackend.dto.portfolio.PortfolioInfo;
import com.example.castfindbackend.entity.PortfolioFolder;
import com.example.castfindbackend.entity.User;
import lombok.experimental.UtilityClass;

import static com.example.castfindbackend.constant.PhotoTypes.PORTFOLIO_PHOTO_TYPE;

@UtilityClass
public class CustomPortfolioMapper {

    public static PortfolioFolder toEntity(PortfolioFolderCreateRequest request, User user) {
        return new PortfolioFolder(
                request.getTitle(),
                request.getDescription(),
                user
        );
    }

    public static PortfolioFolderCreateResponse toCreateResponse(PortfolioFolder entity) {
        return new PortfolioFolderCreateResponse(
                entity.getId(),
                PORTFOLIO_PHOTO_TYPE
        );
    }

    public static PortfolioInfo toInfo(PortfolioFolder entity, PhotoResponse photoResponse) {
        return new PortfolioInfo(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                PORTFOLIO_PHOTO_TYPE,
                photoResponse
        );
    }
}
