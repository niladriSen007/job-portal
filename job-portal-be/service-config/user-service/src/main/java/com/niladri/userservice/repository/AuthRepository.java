package com.niladri.userservice.repository;

import com.niladri.userservice.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(@Email(message = "Email must be valid") @NotBlank(message = "Email is mandatory") String email);

    Optional<UserEntity> findById(String userIdFromToken);
}
