package com.instituto.demoj.Recuperacion.DTO;
import lombok.*;

@Data
@Builder
public class RUserDto {
    private Long id;
     private String name; 
     private String lastname; 
     private String email; 
     private String phone; 
     private String username; 
     private String password; 
     private String role; 
}
