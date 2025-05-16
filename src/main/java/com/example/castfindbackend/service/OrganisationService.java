package com.example.castfindbackend.service;

import com.example.castfindbackend.constant.PhotoTypes;
import com.example.castfindbackend.dto.organisations.OrganisationResponse;
import com.example.castfindbackend.dto.organisations.OrganisationsResponse;
import com.example.castfindbackend.entity.Organisation;
import com.example.castfindbackend.entity.Specialisation;
import com.example.castfindbackend.exceptions.NotFoundByIdException;
import com.example.castfindbackend.mapper.OrganisationMapper;
import com.example.castfindbackend.repository.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.castfindbackend.constant.PhotoTypes.ORGANISATION_PHOTO_TYPE;
import static com.example.castfindbackend.dto.photo.MainPhotoType.ALL;
import static com.example.castfindbackend.dto.photo.MainPhotoType.AVATAR;

@Service
@RequiredArgsConstructor
public class OrganisationService {
    private final OrganisationRepository organisationRepository;
    private final SpecializationService specializationService;
    private final PhotoService photoService;
    private final OrganisationMapper mapper;

    public OrganisationResponse createOrganisation(Organisation organisation,
                                                   List<Long> specializationIds) {
        var specializations = specializationService.findByIds(specializationIds);
        organisation.setSpecialisations(specializationService.findByIds(specializationIds));
        var savedOrganisation = organisationRepository.save(organisation);
        return mapper.fromEntityToResponse(savedOrganisation, specializations.stream()
                .map(Specialisation::getName)
                .toList(), new ArrayList<>());
    }

    public OrganisationResponse findOrganisationById(Long id) {
        var organisation = organisationRepository.findById(id).orElseThrow(
                () -> new NotFoundByIdException(Organisation.class, id));
        var photos = photoService.findPhotosByTypeAndOtherId(id, ORGANISATION_PHOTO_TYPE, ALL);
        return mapper.fromEntityToResponse(organisation, organisation.getSpecialisations()
                .stream()
                .map(Specialisation::getName)
                .toList(), photos);
    }

    public OrganisationsResponse getAll() {
        var organisations = organisationRepository.findAll().stream()
                .map(entity -> {
                    var photos = photoService.findPhotosByTypeAndOtherId(entity.getId(), ORGANISATION_PHOTO_TYPE, AVATAR);
                    return mapper.fromEntityToResponse(entity, entity.getSpecialisations()
                            .stream()
                            .map(Specialisation::getName)
                            .toList(), photos);
                })
                .toList();
        return new OrganisationsResponse(organisations);
    }

    public Organisation findById(Long id) {
        return organisationRepository.findById(id).orElseThrow(
                () -> new NotFoundByIdException(Organisation.class, id));
    }

    public List<OrganisationResponse> getOrganisationBySpecId(Long id) {
        var organisations = organisationRepository.findBySpecialisationId(id);
        return organisations.stream()
                .map(organisation -> {
                    var photos = photoService.findPhotosByTypeAndOtherId(organisation.getId(), ORGANISATION_PHOTO_TYPE, ALL);
                    return mapper.fromEntityToResponse(organisation, organisation.getSpecialisations()
                            .stream()
                            .map(Specialisation::getName)
                            .toList(), photos);
                }).toList();
    }


}
