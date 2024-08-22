package com.irojas.demojwt.User.Persisten.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Roles.Persisten.Entity.RoleEntity;
import com.irojas.demojwt.User.Persisten.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);

    Optional<RoleEntity> findById(Long id);

    Optional<User> findUserById(Long id); 
}
