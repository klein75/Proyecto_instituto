package com.instituto.demoj.Permission.Persistence.Repositoty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.instituto.demoj.Permission.domain.Entity.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    Optional<PermissionEntity> findByPermiso(String string);

}
