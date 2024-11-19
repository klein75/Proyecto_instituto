package com.instituto.demoj.Recuperacion.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instituto.demoj.Recuperacion.entity.RRole;
import com.instituto.demoj.Recuperacion.entity.RUser;

@Repository
public interface RRoleRepository extends JpaRepository<RRole, Long> {

    Optional<RRole> findByRole(String role);

    

}
