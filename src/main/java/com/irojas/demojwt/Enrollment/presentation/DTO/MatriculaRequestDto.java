package com.irojas.demojwt.Enrollment.presentation.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaRequestDto {
    private String nombreUno;
    private String apellidoUno;
    private String folio;
    private String numero;
    private String sede;
    private Date fecha;
    private String estado;
    private Long cursoId;

}
