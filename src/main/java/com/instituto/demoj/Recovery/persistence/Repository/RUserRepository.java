package com.instituto.demoj.Recovery.persistence.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instituto.demoj.Recovery.domain.entity.RUserEntity;

public interface RUserRepository extends JpaRepository<RUserEntity, Long> {

    Optional<RUserEntity> findByUsername(String username);

}
