package com.instituto.demoj.Recovery.persistence.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instituto.demoj.Recovery.domain.entity.RModuleEntity;

public interface RModuleRepository extends JpaRepository<RModuleEntity, Long> {

    Optional<RModuleEntity> findByModule(String string);
}
