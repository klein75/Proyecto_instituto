package com.instituto.demoj.Recuperacion.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rroles")
public class RRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;
    private String description;

    @OneToMany(mappedBy = "role")
    private Set<RRoleModule> roleModules;

}
