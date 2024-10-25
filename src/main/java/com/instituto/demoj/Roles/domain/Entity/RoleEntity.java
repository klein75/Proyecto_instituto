package com.instituto.demoj.Roles.domain.Entity;

import java.util.HashSet;
import java.util.Set;

import com.instituto.demoj.Permission.domain.Entity.PermissionEntity;
import com.instituto.demoj.Utilities.Enum.RoleEnum;
import com.instituto.demoj.Utilities.Enum.StateEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity  {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private RoleEnum rol;

    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private StateEnum estado;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rol_permisos", 
               joinColumns = @JoinColumn(name = "rol"), 
               inverseJoinColumns = @JoinColumn(name = "permiso"))
    private Set<PermissionEntity> permissions = new HashSet<>();
    
    // otros campos...
}



