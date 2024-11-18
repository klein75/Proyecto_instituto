package com.instituto.demoj.Recuperacion.entity;
import jakarta.persistence.*; 
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rprofile")
public class RProfile {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private RUser user;

    private String bio;
    private String image;

}
