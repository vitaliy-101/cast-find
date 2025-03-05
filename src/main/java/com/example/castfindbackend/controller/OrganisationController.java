package com.example.castfindbackend.controller;

import com.example.castfindbackend.dto.organisations.OrganisationCreateRequest;
import com.example.castfindbackend.dto.organisations.OrganisationResponse;
import com.example.castfindbackend.mapper.OrganisationMapper;
import com.example.castfindbackend.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organisation")
@RequiredArgsConstructor
public class OrganisationController {
    private final OrganisationService service;
    private final OrganisationMapper mapper;

    @PostMapping
    public OrganisationResponse create(@RequestBody OrganisationCreateRequest request) {
        return service.createOrganisation(mapper.fromCreateRequestToEntity(request), request.getSpecializationIds());
    }

    @GetMapping("/{id}")
    public OrganisationResponse getOrganisation(@PathVariable Long id) {
        return service.findOrganisationById(id);
    }
}
