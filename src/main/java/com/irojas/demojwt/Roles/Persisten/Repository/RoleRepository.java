package com.irojas.demojwt.Roles.Persisten.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Roles.Persisten.Entity.RoleEntity;

import com.irojas.demojwt.Utilities.Enum.RoleEnum;

public interface RoleRepository extends JpaRepository <RoleEntity, Long> {

	Optional<RoleEntity> findByRol(RoleEntity roleEnum);

    Optional<RoleEntity> findByRol(RoleEnum roleEnum);

}
