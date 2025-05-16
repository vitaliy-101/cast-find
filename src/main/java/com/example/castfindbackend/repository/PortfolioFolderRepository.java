package com.example.castfindbackend.repository;

import com.example.castfindbackend.entity.PortfolioFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioFolderRepository extends JpaRepository<PortfolioFolder, Long> {

    @Query("SELECT p FROM PortfolioFolder p WHERE p.user.id = :userId")
    List<PortfolioFolder> findPortfolioFoldersByUserId(Long userId);
}
