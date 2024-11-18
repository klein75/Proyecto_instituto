package com.instituto.demoj.Recuperacion.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.instituto.demoj.Recuperacion.DTO.RModuleDto;
import com.instituto.demoj.Recuperacion.entity.RModule;
import com.instituto.demoj.Recuperacion.repository.RModuleRepository;

import java.util.List;

@RestController

@RequestMapping("/api/modules")

public class RModuleController {



    @Autowired

    private RModuleRepository moduleRepository;



    @Autowired

    private ModelMapper modelMapper;



    @GetMapping

    public List<RModule> getAllModules() {

        return moduleRepository.findAll();

    }



    @GetMapping("/{id}")

    public ResponseEntity<RModule> getModuleById(@PathVariable Long id) {

        return moduleRepository.findById(id)

                .map(module -> ResponseEntity.ok(module))

                .orElseGet(() -> ResponseEntity.notFound().build());

    }



    @PostMapping

    public ResponseEntity<RModule> createModule(@RequestBody RModuleDto moduleDto) {

        RModule module = modelMapper.map(moduleDto, RModule.class);

        RModule createdModule = moduleRepository.save(module);

        return ResponseEntity.status(201).body(createdModule);

    }



    @PutMapping("/{id}")

    public ResponseEntity<RModule> updateModule(@PathVariable Long id, @RequestBody RModuleDto moduleDto) {

        return moduleRepository.findById(id)

                .map(module -> {

                    modelMapper.map(moduleDto, module);

                    RModule updatedModule = moduleRepository.save(module);

                    return ResponseEntity.ok(updatedModule);

                })

                .orElseGet(() -> ResponseEntity.notFound().build());

    }



    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {

        if (moduleRepository.existsById(id)) {

            moduleRepository.deleteById(id);

            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.notFound().build();

        }

    }}