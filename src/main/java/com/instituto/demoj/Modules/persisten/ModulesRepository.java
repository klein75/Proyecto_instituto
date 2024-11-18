package com.instituto.demoj.Modules.persisten;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instituto.demoj.Modules.Domain.Modules;

@Repository
public interface ModulesRepository extends JpaRepository<Modules, Long> {

}
