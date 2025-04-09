package com.example.castfindbackend.dto.organisations;

import com.example.castfindbackend.dto.photo.PhotoResponse;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private List<PhotoResponse> photos;
}
