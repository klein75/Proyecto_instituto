package com.instituto.demoj.Roles.Persisten.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instituto.demoj.Roles.domain.Entity.RoleEntity;
import com.instituto.demoj.Utilities.Enum.RoleEnum;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRol(RoleEnum roleName);

}
