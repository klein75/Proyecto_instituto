package com.instituto.demoj.News.domain.dto;

import java.time.LocalDate;

import com.instituto.demoj.News.Utils.StateEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {

    private Long id;

    @NotNull(message = "El ID del escritor es obligatorio")
    private Long writerId;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 50, message = "El título no debe superar los 50 caracteres")
    private String title;

    @NotBlank(message = "El subtítulo es obligatorio")
    @Size(max = 150, message = "El subtítulo no debe superar los 150 caracteres")
    private String subtitle;

    @NotBlank(message = "El contenido es obligatorio")
    @Size(min = 100, max = 500, message = "El contenido debe tener entre 100 y 500 caracteres")
    private String content;

    @NotBlank(message = "La imagen es obligatoria")
    private String image;

    @NotNull(message = "La fecha de la noticia es obligatoria")
    private LocalDate date;

    @NotNull(message = "El estado de la noticia es obligatorio")
    private StateEnum state;
}

