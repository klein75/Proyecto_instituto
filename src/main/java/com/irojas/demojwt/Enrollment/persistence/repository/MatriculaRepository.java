package com.irojas.demojwt.Enrollment.persistence.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Enrollment.persistence.entity.MatriculaEntity;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Long> {
    // List<MatriculaEntity> findByAlumnoId(Long alumno);
    List<MatriculaEntity> findByCursoId(Long cursoId);
    List<MatriculaEntity> findByFolio(String folio);
    List<MatriculaEntity> findByNumero(String numero);
    List<MatriculaEntity> findBySede(String sede);
    List<MatriculaEntity> findByEstado(StateEnum estado);
    List<MatriculaEntity> findByAlumnoId(Long id);
}
