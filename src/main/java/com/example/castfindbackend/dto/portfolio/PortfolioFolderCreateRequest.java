package com.example.castfindbackend.dto.portfolio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioFolderCreateRequest {
    private String title;
    private String description;
}
