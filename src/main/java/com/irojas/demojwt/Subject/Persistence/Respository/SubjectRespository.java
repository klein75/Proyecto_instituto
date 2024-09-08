package com.irojas.demojwt.Subject.Persistence.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Subject.Persistence.Entity.SubjectEntity;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

public interface SubjectRespository extends JpaRepository<SubjectEntity, Long> {

    Optional<SubjectEntity> findBySubject(String subject);

    SubjectEntity findByDescription(String description);

    SubjectEntity findByState(StateEnum state);

}
