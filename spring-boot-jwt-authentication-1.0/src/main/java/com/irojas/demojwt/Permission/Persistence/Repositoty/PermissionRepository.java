package com.irojas.demojwt.Permission.Persistence.Repositoty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Permission.Persistence.Entity.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

}
