package com.example.castfindbackend.dto.organisations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrganisationsResponse {
    private List<OrganisationResponse> organisationResponses;
}
