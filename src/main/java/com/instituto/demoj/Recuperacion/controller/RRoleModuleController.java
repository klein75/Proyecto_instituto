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



    @GetMapping

    public List<RRoleModule> getAllRoleModules() {

        return roleModuleRepository.findAll();

    }



    @GetMapping("/{id}")

    public ResponseEntity<RRoleModule> getRoleModuleById(@PathVariable Long id) {

        return roleModuleRepository.findById(id)

                .map(roleModule -> ResponseEntity.ok(roleModule))

                .orElseGet(() -> ResponseEntity.notFound().build());

    }



    @PostMapping
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

    @PutMapping("/{id}")
    public ResponseEntity<RRoleModule> updateRoleModule(@PathVariable Long id, @RequestBody RRoleModuleDto roleModuleDto) {
        RRoleModule roleModule = roleModuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoleModule not found with ID: " + id));

        // Update roleModule properties based on roleModuleDto (e.g., role, module, etc.)

        RRoleModule updatedRoleModule = roleModuleRepository.save(roleModule);
        return ResponseEntity.ok(updatedRoleModule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoleModule(@PathVariable Long id) {
        roleModuleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}