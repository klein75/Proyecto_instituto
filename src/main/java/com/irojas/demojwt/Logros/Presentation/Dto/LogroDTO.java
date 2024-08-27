package com.irojas.demojwt.Logros.Presentation.Dto;

import com.irojas.demojwt.Utilities.Enum.StateEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogroDTO {
    private String logro;
    private String descripcion;
    private String materia;
    private StateEnum estado;
}
