package com.instituto.demoj.Recuperacion.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rrole_module")
public class RRoleModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RRole role;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private RModule module;

}
