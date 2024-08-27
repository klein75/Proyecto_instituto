package com.irojas.demojwt.Logros.Services.Interfaces;

import java.util.List;
import java.util.Optional;

import com.irojas.demojwt.Logros.Persistence.Entity.LogroEntity;
import com.irojas.demojwt.Logros.Presentation.Dto.LogroDTO;

public interface ILogroService {

    LogroEntity createLogro(LogroDTO logroDTO);
    LogroEntity updateLogro(Long id, LogroDTO logroDTO);
    void deleteLogro(Long id);
    List<LogroEntity> getAllLogros();
    Optional<LogroEntity> getLogroById(Long id);
}

