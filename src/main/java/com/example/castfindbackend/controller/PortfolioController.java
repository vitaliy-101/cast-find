package com.example.castfindbackend.controller;

import com.example.castfindbackend.dto.portfolio.PortfolioFolderCreateRequest;
import com.example.castfindbackend.dto.portfolio.PortfolioFolderCreateResponse;
import com.example.castfindbackend.dto.portfolio.UserPortfolioResponse;
import com.example.castfindbackend.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.castfindbackend.utils.UserUtils.extractUserId;

@RestController
@RequestMapping("/api/v1/portfolio")
@RequiredArgsConstructor
public class PortfolioController {
    private final PortfolioService portfolioService;

    @PostMapping
    public PortfolioFolderCreateResponse create(@RequestBody PortfolioFolderCreateRequest request) {
            return portfolioService.create(request, extractUserId());
    }

    @GetMapping
    public UserPortfolioResponse getUserPortfolios() {
        return new UserPortfolioResponse(portfolioService.getUserPortfolios(extractUserId()));
    }
}
