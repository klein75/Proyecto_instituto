package com.irojas.demojwt.reportes.persistence.Entity;

import java.math.BigDecimal;

import com.irojas.demojwt.Enrollment.persistence.entity.MatriculaEntity;
import com.irojas.demojwt.OpenCourse.Persistence.Entity.OpenCourseEntity;
import com.irojas.demojwt.Period.Persistence.Entity.PeriodEntity;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reportes")
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal nota;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal fallas;

    @Column(length = 255)
    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "alumno", referencedColumnName = "id", nullable = false)
    private MatriculaEntity alumno;

    @ManyToOne
    @JoinColumn(name = "materia", referencedColumnName = "id", nullable = false)
    private OpenCourseEntity materia;

    @ManyToOne
    @JoinColumn(name = "periodo", referencedColumnName = "id", nullable = false)
    private PeriodEntity periodo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StateEnum estado;
}

