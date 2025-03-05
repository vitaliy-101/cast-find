package com.example.castfindbackend.dto.organisations;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrganisationResponse {
    private Long id;
    private String name;
    private String description;
    private List<String> specializationNames;
}
