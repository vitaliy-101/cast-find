package com.example.castfindbackend.service;

import com.example.castfindbackend.constant.PhotoTypes;
import com.example.castfindbackend.dto.specialization.SpecializationResponse;
import com.example.castfindbackend.entity.Specialisation;
import com.example.castfindbackend.exceptions.NotFoundByIdException;
import com.example.castfindbackend.mapper.SpecializationMapper;
import com.example.castfindbackend.repository.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.example.castfindbackend.constant.PhotoTypes.SPEC_PHOTO_TYPE;

@Service
@RequiredArgsConstructor
public class SpecializationService {
    private final SpecializationRepository repository;
    private final SpecializationMapper mapper;
    private final PhotoService photoService;

    public Specialisation create(Specialisation specialisation) {
        return repository.save(specialisation);
    }

    public List<SpecializationResponse> getAll() {
        var specs = repository.findAll();
        return specs.stream()
                .map(spec -> mapper.fromEntityToResponse(spec, photoService.findAvatar(spec.getId(), SPEC_PHOTO_TYPE)))
                .toList();
    }

    public List<Specialisation> findByIds(List<Long> ids) {
        var specializations = repository.findSpecialisations(ids);
        if (ids.size() != specializations.size()) {
            throw new NotFoundByIdException(Specialisation.class, -1L);
        }
        return specializations;
    }
}
