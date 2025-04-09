package com.example.castfindbackend.repository;

import com.example.castfindbackend.entity.Organisation;
import com.example.castfindbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);
    Boolean existsByNickname(String nickname);

}
