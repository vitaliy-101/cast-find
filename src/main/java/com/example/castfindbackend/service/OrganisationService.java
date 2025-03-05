package com.example.castfindbackend.service;

import com.example.castfindbackend.dto.organisations.OrganisationResponse;
import com.example.castfindbackend.entity.Organisation;
import com.example.castfindbackend.entity.Specialisation;
import com.example.castfindbackend.exceptions.NotFoundByIdException;
import com.example.castfindbackend.mapper.OrganisationMapper;
import com.example.castfindbackend.repository.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganisationService {
    private final OrganisationRepository organisationRepository;
    private final SpecializationService specializationService;
    private final OrganisationMapper mapper;

    public OrganisationResponse createOrganisation(Organisation organisation,
                                                   List<Long> specializationIds) {
        var specializations = specializationService.findByIds(specializationIds);
        organisation.setSpecialisations(specializationService.findByIds(specializationIds));
        var savedOrganisation = organisationRepository.save(organisation);
        return mapper.fromEntityToResponse(savedOrganisation, specializations.stream()
                .map(Specialisation::getName)
                .toList());
    }

    public OrganisationResponse findOrganisationById(Long id) {
        var organisation = organisationRepository.findById(id).orElseThrow(
                () -> new NotFoundByIdException(Organisation.class, id));
        return mapper.fromEntityToResponse(organisation, organisation.getSpecialisations()
                .stream()
                .map(Specialisation::getName)
                .toList());
    }
}
