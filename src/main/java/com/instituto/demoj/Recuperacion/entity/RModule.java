package com.instituto.demoj.Recuperacion.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rmodule")
public class RModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String module;
    private String description;

    @OneToMany(mappedBy = "module")
    private Set<RRoleModule> roleModules;

}
