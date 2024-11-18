package com.instituto.demoj.Recuperacion.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.instituto.demoj.Recuperacion.DTO.RRoleDto;
import com.instituto.demoj.Recuperacion.entity.RRole;
import com.instituto.demoj.Recuperacion.repository.RRoleRepository;

import java.util.List;

@RestController

@RequestMapping("/api/roles")

public class RRoleController {



    @Autowired

    private RRoleRepository roleRepository;



    @Autowired

    private ModelMapper modelMapper;



    @GetMapping

    public List<RRole> getAllRoles() {

        return roleRepository.findAll();

    }



    @GetMapping("/{id}")

    public ResponseEntity<RRole> getRoleById(@PathVariable Long id) {

        return roleRepository.findById(id)

                .map(role -> ResponseEntity.ok(role))

                .orElseGet(() -> ResponseEntity.notFound().build());

    }



    @PostMapping

    public ResponseEntity<RRole> createRole(@RequestBody RRoleDto roleDto) {

        RRole role = modelMapper.map(roleDto, RRole.class);

        RRole createdRole = roleRepository.save(role);

        return ResponseEntity.status(201).body(createdRole);

    }



    @PutMapping("/{id}")

    public ResponseEntity<RRole> updateRole(@PathVariable Long id, @RequestBody RRoleDto roleDto) {

        return roleRepository.findById(id)

                .map(role -> {

                    modelMapper.map(roleDto, role);

                    RRole updatedRole = roleRepository.save(role);

                    return ResponseEntity.ok(updatedRole);

                })

                .orElseGet(() -> ResponseEntity.notFound().build());

    }



    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {

        if (roleRepository.existsById(id)) {

            roleRepository.deleteById(id);

            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.notFound().build();

        }

    }}