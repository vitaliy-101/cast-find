package com.example.castfindbackend.mapper;

import com.example.castfindbackend.dto.specialization.SpecializationCreateRequest;
import com.example.castfindbackend.dto.specialization.SpecializationResponse;
import com.example.castfindbackend.entity.Specialisation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpecializationMapper {
    Specialisation fromCreateRequestToEntity(SpecializationCreateRequest request);

    SpecializationResponse fromEntityToResponse(Specialisation specialisation);

    List<SpecializationResponse> fromEntityListToResponseList(List<Specialisation> specialisation);
}
