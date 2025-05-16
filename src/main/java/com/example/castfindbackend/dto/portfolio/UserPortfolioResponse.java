package com.example.castfindbackend.dto.portfolio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserPortfolioResponse {
    private List<PortfolioInfo> portfolioInfos;
}
