package com.instituto.demoj.Modules.business.service;

import java.util.List;
import java.util.Optional;

import com.instituto.demoj.Modules.Domain.Modules;

public interface ModulesService {


    Modules createModule(Modules module);

    Modules updateModule(Long id, Modules module);

    void deleteModule(Long id);

    Optional<Modules> getModuleById(Long id);

    List<Modules> getAllModules();

}

