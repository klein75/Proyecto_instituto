package com.instituto.demoj.Modules.Domain;
import jakarta.persistence.*; 
import lombok.*; 
import java.util.Set;

import com.instituto.demoj.Roles.domain.Entity.RoleEntity;
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @Entity 
 @Table(name= "Module")
public class Modules {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 
    
    private String module; 
    
    private String description;

    @ManyToMany
    @JoinTable(
        name = "role_module",
        joinColumns = @JoinColumn(name = "module_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roleModules; 

}


