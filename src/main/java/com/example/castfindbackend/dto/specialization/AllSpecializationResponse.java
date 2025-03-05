package com.example.castfindbackend.dto.specialization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AllSpecializationResponse {
    private List<SpecializationResponse> specializations;
}
