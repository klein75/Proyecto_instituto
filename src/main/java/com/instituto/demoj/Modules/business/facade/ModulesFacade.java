package com.instituto.demoj.Modules.business.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.instituto.demoj.Modules.Domain.Modules;
import com.instituto.demoj.Modules.business.service.ModulesService;

@Component
public class ModulesFacade {

    private final ModulesService modulesService;

    @Autowired

    public ModulesFacade(ModulesService modulesService) {

        this.modulesService = modulesService;

    }

    public ResponseEntity<Modules> createModule(Modules module) {

        Modules createdModule = modulesService.createModule(module);

        return new ResponseEntity<>(createdModule, HttpStatus.CREATED);

    }

    public ResponseEntity<Modules> updateModule(Long id, Modules module) {

        Modules updatedModule = modulesService.updateModule(id, module);

        if (updatedModule != null) {

            return new ResponseEntity<>(updatedModule, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    public ResponseEntity<Void> deleteModule(Long id) {

        modulesService.deleteModule(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    public ResponseEntity<Modules> getModuleById(Long id) {

        Optional<Modules> module = modulesService.getModuleById(id);

        return module.map(m -> new ResponseEntity<>(m, HttpStatus.OK))

                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    public ResponseEntity<List<Modules>> getAllModules() {

        List<Modules> modules = modulesService.getAllModules();

        return new ResponseEntity<>(modules, HttpStatus.OK);

    }
}
