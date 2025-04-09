package com.example.castfindbackend.mapper;

import com.example.castfindbackend.dto.auth.AuthRequest;
import com.example.castfindbackend.dto.organisations.OrganisationCreateRequest;
import com.example.castfindbackend.dto.organisations.OrganisationInfo;
import com.example.castfindbackend.dto.organisations.OrganisationResponse;
import com.example.castfindbackend.dto.photo.PhotoData;
import com.example.castfindbackend.dto.photo.PhotoResponse;
import com.example.castfindbackend.entity.Organisation;
import com.example.castfindbackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrganisationMapper {

    Organisation fromCreateRequestToEntity(OrganisationCreateRequest request);

    @Mapping(target = "specializationNames", source = "specializationNames")
    @Mapping(target = "photos", source = "photos")
    OrganisationResponse fromEntityToResponse(Organisation organisation,
                                              List<String> specializationNames,
                                              List<PhotoResponse> photos);

    OrganisationInfo toInfo(Organisation organisation);
}
