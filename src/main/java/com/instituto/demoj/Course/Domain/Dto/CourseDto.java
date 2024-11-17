package com.instituto.demoj.Course.Domain.Dto;

import com.instituto.demoj.Course.utils.TitleEnum;
import com.instituto.demoj.Course.utils.TypeEnum;
import com.instituto.demoj.Utilities.Enum.StateEnum;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private Long id;

    @NotBlank(message = "el curso es obligatorio")
    private String course;

    @NotBlank(message = "Los destalles del curso son obligatorio")
    private String details;

    @NotBlank(message = "La descripción del curso es obligatoria")
    private String description;

    @NotBlank(message = "La duración del curso es obligatioria")
    private String duration;

    private TitleEnum title;


    private TypeEnum type;

   
    private StateEnum state;
}
