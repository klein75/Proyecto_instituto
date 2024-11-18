package com.instituto.demoj.Recuperacion.DTO;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RProfileDto {
    private Long userId;
     private String bio; 
     private String image;

}
