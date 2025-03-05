package com.example.castfindbackend.service;

import com.example.castfindbackend.entity.Specialisation;
import com.example.castfindbackend.exceptions.NotFoundByIdException;
import com.example.castfindbackend.repository.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SpecializationService {
    private final SpecializationRepository repository;

    public Specialisation create(Specialisation specialisation) {
        return repository.save(specialisation);
    }

    public List<Specialisation> getAll() {
        return repository.findAll();
    }

    public Set<Specialisation> findByIds(List<Long> ids) {
        var specializations = repository.findSpecialisations(ids);
        if (ids.size() != specializations.size()) {
            throw new NotFoundByIdException(Specialisation.class, -1L);
        }
        return specializations;
    }
}
