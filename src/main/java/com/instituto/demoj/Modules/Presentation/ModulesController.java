package com.instituto.demoj.Modules.Presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instituto.demoj.Modules.Domain.Modules;
import com.instituto.demoj.Modules.business.facade.ModulesFacade;

@RestController
@RequestMapping("/api/modules")
public class ModulesController {

    private final ModulesFacade modulesFacade;

    @Autowired
    public ModulesController(ModulesFacade modulesFacade) {

        this.modulesFacade = modulesFacade;

    }

    @PostMapping("/create")
    public ResponseEntity<Modules> createModule(@RequestBody Modules module) {
        return modulesFacade.createModule(module);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Modules> updateModule(@PathVariable Long id, @RequestBody Modules module) {
        return modulesFacade.updateModule(id, module);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        return modulesFacade.deleteModule(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Modules> getModuleById(@PathVariable Long id) {
        return modulesFacade.getModuleById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Modules>> getAllModules() {
        return modulesFacade.getAllModules();

    }
}
