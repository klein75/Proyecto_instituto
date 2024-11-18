package com.instituto.demoj.Recuperacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instituto.demoj.Recuperacion.entity.RProfile;

@Repository
public interface RProfileRepository  extends  JpaRepository<RProfile,Long>{
}
