package com.example.castfindbackend.repository;

import com.example.castfindbackend.entity.Specialisation;
import com.example.castfindbackend.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialisation, Long> {
    @Query("SELECT s FROM Specialisation s WHERE s.id IN :ids")
    List<Specialisation> findSpecialisations(List<Long> ids);
}
