package com.example.castfindbackend.dto.portfolio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PortfolioFolderCreateResponse {
    private Long id;
    private String photoType;
}
