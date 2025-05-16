package com.example.castfindbackend.dto.portfolio;

import com.example.castfindbackend.dto.photo.PhotoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PortfolioInfo {
    private Long id;
    private String title;
    private String description;
    private String photoType;
    private PhotoResponse avatar;
}
