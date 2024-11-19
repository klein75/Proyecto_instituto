package com.instituto.demoj.Recuperacion.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.instituto.demoj.Recuperacion.DTO.RRoleModuleDto;
import com.instituto.demoj.Recuperacion.entity.RModule;
import com.instituto.demoj.Recuperacion.entity.RRole;
import com.instituto.demoj.Recuperacion.entity.RRoleModule;
import com.instituto.demoj.Recuperacion.repository.RModuleRepository;
import com.instituto.demoj.Recuperacion.repository.RRoleModuleRepository;
import com.instituto.demoj.Recuperacion.repository.RRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/api/role-modules")

public class RRoleModuleController {



    @Autowired

    private RRoleModuleRepository roleModuleRepository;



    @Autowired

    private RRoleRepository roleRepository;



    @Autowired

    private RModuleRepository moduleRepository;



    @Autowired

    private ModelMapper modelMapper;



    @GetMapping("/all")
    public List<RRoleModuleDto> getAllRoleModules() {
        return roleModuleRepository.findAll()
                .stream()
                .map(roleModule -> modelMapper.map(roleModule, RRoleModuleDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RRoleModuleDto> getRoleModuleById(@PathVariable Long id) {
        return roleModuleRepository.findById(id)
                .map(roleModule -> ResponseEntity.ok(modelMapper.map(roleModule, RRoleModuleDto.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PostMapping("/create")
    public ResponseEntity<RRoleModule> createRoleModule(@RequestBody RRoleModuleDto roleModuleDto) {
        RRole role = roleRepository.findById(roleModuleDto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleModuleDto.getRoleId()));
        RModule module = moduleRepository.findById(roleModuleDto.getModuleId())
                .orElseThrow(() -> new RuntimeException("Module not found with ID: " + roleModuleDto.getModuleId()));

        RRoleModule roleModule = new RRoleModule();
        roleModule.setRole(role);
        roleModule.setModule(module);
        // Set other properties of roleModule as needed

        RRoleModule savedRoleModule = roleModuleRepository.save(roleModule);
        return ResponseEntity.ok(savedRoleModule);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RRoleModuleDto> updateRoleModule(@PathVariable Long id, @RequestBody RRoleModuleDto roleModuleDto) {
        // Buscar el RRoleModule existente
        RRoleModule roleModule = roleModuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoleModule not found with ID: " + id));
        
        // Mapear los cambios desde el DTO a la entidad existente
        modelMapper.map(roleModuleDto, roleModule);
        
        // Si hay relaciones que necesitan actualización, manejar aquí
        if (roleModuleDto.getRoleId() != null) {
            RRole role = roleRepository.findById(roleModuleDto.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleModuleDto.getRoleId()));
            roleModule.setRole(role);
        }
    
        if (roleModuleDto.getModuleId() != null) {
            RModule module = moduleRepository.findById(roleModuleDto.getModuleId())
                    .orElseThrow(() -> new RuntimeException("Module not found with ID: " + roleModuleDto.getModuleId()));
            roleModule.setModule(module);
        }
    
        // Guardar los cambios
        RRoleModule updatedRoleModule = roleModuleRepository.save(roleModule);
        
        // Mapear la entidad actualizada al DTO
        RRoleModuleDto updatedRoleModuleDto = modelMapper.map(updatedRoleModule, RRoleModuleDto.class);
        return ResponseEntity.ok(updatedRoleModuleDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRoleModule(@PathVariable Long id) {
        roleModuleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}