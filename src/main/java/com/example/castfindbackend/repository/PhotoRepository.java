package com.example.castfindbackend.repository;

import com.example.castfindbackend.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findPhotoByOtherIdAndType(Long otherId, String type);
    Photo findPhotoByOtherIdAndTypeAndMain(Long otherId, String type, boolean main);
}
