package com.irojas.demojwt.schedule.persistence.entity;

import java.sql.Date;

import com.irojas.demojwt.Utilities.Enum.StateEnum;

import java.sql.Time;

import com.irojas.demojwt.Utilities.Enum.DayEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "horarios")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "día", nullable = false)
    private DayEnum day;

    @Column(name = "inicio", nullable = false)
    private Time start;

    @Column(name = "fin", nullable = false)
    private Time end;

    @Column(name = "descripcion", length = 255, nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private StateEnum state;
}
