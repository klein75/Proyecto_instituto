package com.instituto.demoj.Recovery.persistence.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instituto.demoj.Recovery.domain.entity.RProfileEntity;
import com.instituto.demoj.Recovery.domain.entity.RUserEntity;

public interface RProfileRepository extends JpaRepository<RProfileEntity, Long> {

    Optional<RProfileEntity> findByUser(RUserEntity user);

}
