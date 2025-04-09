package com.example.castfindbackend.repository;

import com.example.castfindbackend.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    @Query("SELECT o FROM Organisation o JOIN o.specialisations s WHERE s.id = :specId")
    List<Organisation> findBySpecialisationId(@Param("specId") Long specId);
}
