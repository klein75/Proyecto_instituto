package com.irojas.demojwt.Enrollment.persistence.entity;

import java.util.Collection;
import java.util.Date;

import com.irojas.demojwt.OpenCourse.Persistence.Entity.OpenCourseEntity;
import com.irojas.demojwt.User.Persisten.entity.User;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matriculas")
public class MatriculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false, length = 10)
    private String folio;

    @Column(nullable = false, length = 10)
    private String numero;

    @Column(nullable = false, length = 20)
    private String sede;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StateEnum estado;

    @ManyToOne
    @JoinColumn(name = "alumno", referencedColumnName = "id", nullable = false)
    private User alumno;

    @ManyToOne
    @JoinColumn(name = "curso", referencedColumnName = "id", nullable = false)
    private OpenCourseEntity curso;




  

    // Getters y setters
}
