package com.irojas.demojwt.Enrollment.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irojas.demojwt.Enrollment.Services.interfaces.IMatriculaService;
import com.irojas.demojwt.Enrollment.persistence.entity.MatriculaEntity;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private IMatriculaService matriculaService;

    // Obtener todas las matrículas
    @GetMapping
    public ResponseEntity<List<MatriculaEntity>> getAllMatriculas() {
        List<MatriculaEntity> matriculas = matriculaService.findAll();
        return ResponseEntity.ok(matriculas);
    }

    // Obtener matrículas por ID del alumno
    @GetMapping("/alumno/{id}")
    public ResponseEntity<List<MatriculaEntity>> getMatriculasByAlumnoId(@PathVariable Long id) {
        List<MatriculaEntity> matriculas = matriculaService.findByAlumnoId(id);
        return ResponseEntity.ok(matriculas);
    }

    // Obtener matrículas por ID del curso
    @GetMapping("/curso/{id}")
    public ResponseEntity<List<MatriculaEntity>> getMatriculasByCursoId(@PathVariable Long id) {
        List<MatriculaEntity> matriculas = matriculaService.findByCursoId(id);
        return ResponseEntity.ok(matriculas);
    }

    // Obtener matrículas por folio
    @GetMapping("/folio/{folio}")
    public ResponseEntity<List<MatriculaEntity>> getMatriculasByFolio(@PathVariable String folio) {
        List<MatriculaEntity> matriculas = matriculaService.findByFolio(folio);
        return ResponseEntity.ok(matriculas);
    }

    // Obtener matrículas por número
    @GetMapping("/numero/{numero}")
    public ResponseEntity<List<MatriculaEntity>> getMatriculasByNumero(@PathVariable String numero) {
        List<MatriculaEntity> matriculas = matriculaService.findByNumero(numero);
        return ResponseEntity.ok(matriculas);
    }

    // Obtener matrículas por sede
    @GetMapping("/sede/{sede}")
    public ResponseEntity<List<MatriculaEntity>> getMatriculasBySede(@PathVariable String sede) {
        List<MatriculaEntity> matriculas = matriculaService.findBySede(sede);
        return ResponseEntity.ok(matriculas);
    }

    // Obtener matrículas por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<MatriculaEntity>> getMatriculasByEstado(@PathVariable StateEnum estado) {
        List<MatriculaEntity> matriculas = matriculaService.findByState(estado);
        return ResponseEntity.ok(matriculas);
    }

    // Obtener matrícula por ID
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaEntity> getMatriculaById(@PathVariable Long id) {
        MatriculaEntity matricula = matriculaService.findById(id);
        return ResponseEntity.ok(matricula);
    }

    // Crear una nueva matrícula
    @PostMapping("create")
    public ResponseEntity<MatriculaEntity> createMatricula(@RequestBody MatriculaEntity matricula) {
        MatriculaEntity nuevaMatricula = matriculaService.create(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMatricula);
    }

    // Actualizar una matrícula existente
    @PutMapping("/{id}")
    public ResponseEntity<MatriculaEntity> updateMatricula(@PathVariable Long id, @RequestBody MatriculaEntity matricula) {
        MatriculaEntity actualizada = matriculaService.update(id, matricula);
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar una matrícula
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatricula(@PathVariable Long id) {
        matriculaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}