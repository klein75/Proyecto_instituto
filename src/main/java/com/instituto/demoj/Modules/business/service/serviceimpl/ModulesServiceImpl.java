package com.instituto.demoj.Modules.business.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instituto.demoj.Modules.Domain.Modules;
import com.instituto.demoj.Modules.business.service.ModulesService;
import com.instituto.demoj.Modules.persisten.ModulesRepository;
@Service
public class ModulesServiceImpl implements ModulesService {

    private final ModulesRepository modulesRepository;

    @Autowired

    public ModulesServiceImpl(ModulesRepository modulesRepository) {

        this.modulesRepository = modulesRepository;

    }

    @Override

    public Modules createModule(Modules module) {

        return modulesRepository.save(module);

    }

    @Override

    public Modules updateModule(Long id, Modules module) {

        if (modulesRepository.existsById(id)) {

            module.setId(id);

            return modulesRepository.save(module);

        }

        return null;

    }

    @Override

    public void deleteModule(Long id) {

        modulesRepository.deleteById(id);

    }

    @Override

    public Optional<Modules> getModuleById(Long id) {

        return modulesRepository.findById(id);

    }

    @Override

    public List<Modules> getAllModules() {

        return modulesRepository.findAll();

    }
}
