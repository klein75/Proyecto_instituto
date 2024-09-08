package com.irojas.demojwt.User.Persisten.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.irojas.demojwt.Enrollment.persistence.entity.MatriculaEntity;
import com.irojas.demojwt.Roles.Persisten.Entity.RoleEntity;
import com.irojas.demojwt.User.Persisten.entity.User;

public interface UserRepository extends JpaRepository<User,Long>,JpaSpecificationExecutor<User> {

    Optional<User> findByUsername(String username);
    Optional<User> findUserById(Long id);
    Optional<MatriculaEntity> findByNombreUnoAndApellidoUno(String nombreUno, String apellidoUno);
}
