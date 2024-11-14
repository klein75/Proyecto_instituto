package com.instituto.demoj.Recovery.persistence.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instituto.demoj.Recovery.domain.entity.RRoleEntity;

public interface RRoleRepository extends JpaRepository<RRoleEntity, Long> {
    Optional<RRoleEntity> findByRole(String role);

}
