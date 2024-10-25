package com.instituto.demoj.User.Persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.instituto.demoj.User.domain.entity.User;
import com.instituto.demoj.Utilities.Enum.RoleEnum;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsernameAndIdNot(String username, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findUserById(Long id);

    List<User> findByRolesRol(RoleEnum rol);
}
