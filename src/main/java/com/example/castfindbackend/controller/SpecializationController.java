package com.example.castfindbackend.controller;

import com.example.castfindbackend.dto.specialization.AllSpecializationResponse;
import com.example.castfindbackend.dto.specialization.SpecializationCreateRequest;
import com.example.castfindbackend.dto.specialization.SpecializationResponse;
import com.example.castfindbackend.mapper.SpecializationMapper;
import com.example.castfindbackend.service.SpecializationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/specialization")
@RequiredArgsConstructor
public class SpecializationController {
    private final SpecializationService service;
    private final SpecializationMapper mapper;
    @PostMapping
    public SpecializationResponse create(@RequestBody SpecializationCreateRequest request) {
        return mapper.fromEntityToResponse(service.create(
                mapper.fromCreateRequestToEntity(request)
        ));
    }

    @GetMapping("/all")
    public AllSpecializationResponse getAll() {
        return new AllSpecializationResponse(
                mapper.fromEntityListToResponseList(service.getAll())
        );
    }

}
