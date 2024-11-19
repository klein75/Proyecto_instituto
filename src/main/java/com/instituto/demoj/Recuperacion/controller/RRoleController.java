package com.instituto.demoj.Recuperacion.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.instituto.demoj.Recuperacion.DTO.RRoleDto;
import com.instituto.demoj.Recuperacion.entity.RRole;
import com.instituto.demoj.Recuperacion.repository.RRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/api/roles")

public class RRoleController {



    @Autowired

    private RRoleRepository roleRepository;



    @Autowired

    private ModelMapper modelMapper;



    @GetMapping("/all")
    public List<RRoleDto> getAllRoles() {
        return roleRepository.findAll()
        .stream()
        .map(RRole -> modelMapper.map(RRole, RRoleDto.class))
        .collect(Collectors.toList());

    }



    @GetMapping("/get/{id}")
    public ResponseEntity<RRoleDto> getRoleById(@PathVariable Long id) {
        return roleRepository.findById(id)
                .map(role -> ResponseEntity.ok(modelMapper.map(role, RRoleDto.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




    @PostMapping("/create")

    public ResponseEntity<RRole> createRole(@RequestBody RRoleDto roleDto) {

        RRole role = modelMapper.map(roleDto, RRole.class);

        RRole createdRole = roleRepository.save(role);

        return ResponseEntity.status(201).body(createdRole);

    }



    @PutMapping("/update/{id}")

    public ResponseEntity<RRole> updateRole(@PathVariable Long id, @RequestBody RRoleDto roleDto) {

        return roleRepository.findById(id)

                .map(role -> {

                    modelMapper.map(roleDto, role);

                    RRole updatedRole = roleRepository.save(role);

                    return ResponseEntity.ok(updatedRole);

                })

                .orElseGet(() -> ResponseEntity.notFound().build());

    }



    @DeleteMapping("/delete/{id}")

    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {

        if (roleRepository.existsById(id)) {

            roleRepository.deleteById(id);

            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.notFound().build();

        }

    }}