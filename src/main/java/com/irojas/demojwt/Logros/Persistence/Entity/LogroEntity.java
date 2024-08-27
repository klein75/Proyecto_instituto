package com.irojas.demojwt.Logros.Persistence.Entity;

import com.irojas.demojwt.Subject.Persistence.Entity.SubjectEntity;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
@Table(name = "logros")
public class LogroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "logro", unique = true, nullable = false)
    private String logro;

    @Column(name = "descripcion", length = 255, nullable = true)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private StateEnum estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia", nullable = false)
    private SubjectEntity materia;
}
