package com.niladri.userservice.repository;

import com.niladri.userservice.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session,Long> {
    List<Session> findByUserEmail(String email);

    Optional<Session> findByRefreshToken(String refreshToken);
}
