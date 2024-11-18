package com.instituto.demoj.Recuperacion.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.instituto.demoj.Recuperacion.repository.RRoleRepository;
import com.instituto.demoj.Recuperacion.repository.RUserRepositoy;
import com.instituto.demoj.Recuperacion.DTO.RUserDto;
import com.instituto.demoj.Recuperacion.entity.RRole;
import com.instituto.demoj.Recuperacion.entity.RUser;

import java.util.List;

@RestController

@RequestMapping("/api/rusers")

public class RUserController {

    @Autowired
    private RUserRepositoy userRepository;

    @Autowired
    private RRoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/all")
    public List<RUser> getAllRusers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RUser> getRuserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<RUser> createRuser(@RequestBody RUserDto userDto) {
        RUser user = modelMapper.map(userDto, RUser.class);
        RRole role = roleRepository.findById(userDto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + userDto.getRoleId()));
        user.setRole(role);
        RUser createdUser = userRepository.save(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RUser> updateRUser(@PathVariable Long id, @RequestBody RUserDto userDto) {
        return userRepository.findById(id)
                .map(user -> {
                    modelMapper.map(userDto, user);
                    RRole role = roleRepository.findById(userDto.getRoleId())
                            .orElseThrow(() -> new RuntimeException("Role not found with ID: " + userDto.getRoleId()));
                    user.setRole(role);
                    RUser updatedUser = userRepository.save(user);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
