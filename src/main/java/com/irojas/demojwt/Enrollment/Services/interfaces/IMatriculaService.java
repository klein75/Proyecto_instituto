package com.irojas.demojwt.Enrollment.Services.interfaces;
import java.util.List;

import com.irojas.demojwt.Enrollment.persistence.entity.MatriculaEntity;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

public interface IMatriculaService {

    List<MatriculaEntity> findAll();
    List<MatriculaEntity> findByAlumnoId(Long id);
    List<MatriculaEntity> findByCursoId(Long id);
    List<MatriculaEntity> findByFolio(String folio);
    List<MatriculaEntity> findByNumero(String numero);
    List<MatriculaEntity> findBySede(String sede);
    List<MatriculaEntity> findByState(StateEnum estado);
    MatriculaEntity findById(Long id);
    MatriculaEntity create(MatriculaEntity matricula);
    MatriculaEntity update(Long id, MatriculaEntity matricula);
    void delete(Long id);
}