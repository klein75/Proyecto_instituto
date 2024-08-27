package com.irojas.demojwt.Logros.Persistence.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Logros.Persistence.Entity.LogroEntity;

public interface LogroRepository extends JpaRepository<LogroEntity, Long> {
    Optional<LogroEntity> findByLogro(String logro);

}
