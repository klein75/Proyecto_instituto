package com.irojas.demojwt.Logros.Services.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Logros.Persistence.Entity.LogroEntity;
import com.irojas.demojwt.Logros.Persistence.Repository.LogroRepository;
import com.irojas.demojwt.Logros.Presentation.Dto.LogroDTO;
import com.irojas.demojwt.Logros.Services.Interfaces.ILogroService;
import com.irojas.demojwt.Subject.Persistence.Entity.SubjectEntity;
import com.irojas.demojwt.Subject.Persistence.Respository.SubjectRespository;

@Service
public class LogroServiceImpl implements ILogroService {

    @Autowired
    private LogroRepository logroRepository;

    @Autowired
    private SubjectRespository subjectRepository;

    @Override
    public LogroEntity createLogro(LogroDTO logroDTO) {
        SubjectEntity materia = subjectRepository.findBySubject(logroDTO.getMateria())
                .orElseThrow(() -> new RuntimeException("Materia not found"));

        LogroEntity logro = LogroEntity.builder()
                .logro(logroDTO.getLogro())
                .descripcion(logroDTO.getDescripcion())
                .materia(materia)
                .estado(logroDTO.getEstado())
                .build();

        return logroRepository.save(logro);
    }

    @Override
    public LogroEntity updateLogro(Long id, LogroDTO logroDTO) {
        LogroEntity existingLogro = logroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Logro not found"));

        SubjectEntity materia = subjectRepository.findBySubject(logroDTO.getMateria())
                .orElseThrow(() -> new RuntimeException("Materia not found"));

        existingLogro.setLogro(logroDTO.getLogro());
        existingLogro.setDescripcion(logroDTO.getDescripcion());
        existingLogro.setMateria(materia);
        existingLogro.setEstado(logroDTO.getEstado());

        return logroRepository.save(existingLogro);
    }

    @Override
    public void deleteLogro(Long id) {
        LogroEntity existingLogro = logroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Logro not found"));
        logroRepository.delete(existingLogro);
    }

    @Override
    public List<LogroEntity> getAllLogros() {
        return logroRepository.findAll();
    }

    @Override
    public Optional<LogroEntity> getLogroById(Long id) {
        return logroRepository.findById(id);
    }
}


