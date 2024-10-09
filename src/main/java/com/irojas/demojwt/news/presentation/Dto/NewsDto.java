package com.irojas.demojwt.news.presentation.Dto;

import java.time.LocalDateTime;

import com.irojas.demojwt.Utilities.Enum.visualEnum;

import lombok.Data;

@Data
public class NewsDto {
 private Long id;
    private String titulo;
    private String subtitulo;
    private String descripcion;
    private String imagen;
    private LocalDateTime fecha;
    private LocalDateTime fechaCreacion;
    private visualEnum privacidad;
    private Long autorId;
}
