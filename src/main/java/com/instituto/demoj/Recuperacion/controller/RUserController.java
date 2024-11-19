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
import java.util.stream.Collectors;

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
    public List<RUserDto> getAllRUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    RUserDto userDto = modelMapper.map(user, RUserDto.class);
                    userDto.setRole(user.getRole().getRole()); 
                    return userDto;
                })
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RUserDto> getRUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {

                    RUserDto userDto = modelMapper.map(user, RUserDto.class);
                    userDto.setRole(user.getRole().getRole()); 
                    return ResponseEntity.ok(userDto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<RUserDto> createRUser(@RequestBody RUserDto userDto) {
        RUser user = modelMapper.map(userDto, RUser.class);
        RRole role = roleRepository.findByRole(userDto.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + userDto.getRole()));
        user.setRole(role);
        RUser createdUser = userRepository.save(user);
        RUserDto createdUserDto = modelMapper.map(createdUser, RUserDto.class);
        createdUserDto.setRole(createdUser.getRole().getRole()); 
        
        return ResponseEntity.status(201).body(createdUserDto);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RUserDto> updateRUser(@PathVariable Long id, @RequestBody RUserDto userDto) {
        return userRepository.findById(id)
                .map(user -> {
                    modelMapper.map(userDto, user);
                    RRole role = roleRepository.findByRole(userDto.getRole())
                            .orElseThrow(() -> new RuntimeException("Role not found with name: " + userDto.getRole()));
                    user.setRole(role);
                    RUser updatedUser = userRepository.save(user);
                    RUserDto updatedUserDto = modelMapper.map(updatedUser, RUserDto.class);
                    updatedUserDto.setRole(updatedUser.getRole().getRole()); 
                    
                    return ResponseEntity.ok(updatedUserDto);
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