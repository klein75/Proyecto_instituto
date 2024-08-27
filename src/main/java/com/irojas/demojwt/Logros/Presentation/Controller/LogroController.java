package com.irojas.demojwt.Logros.Presentation.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irojas.demojwt.Logros.Persistence.Entity.LogroEntity;
import com.irojas.demojwt.Logros.Presentation.Dto.LogroDTO;
import com.irojas.demojwt.Logros.Services.Interfaces.ILogroService;

@RestController
@RequestMapping("/api/logros")
public class LogroController {

    @Autowired
    private ILogroService logroService;

    @PostMapping
    public ResponseEntity<LogroEntity> createLogro(@RequestBody LogroDTO logroDTO) {
        LogroEntity newLogro = logroService.createLogro(logroDTO);
        return new ResponseEntity<>(newLogro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogroEntity> updateLogro(@PathVariable Long id, @RequestBody LogroDTO logroDTO) {
        LogroEntity updatedLogro = logroService.updateLogro(id, logroDTO);
        return new ResponseEntity<>(updatedLogro, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogro(@PathVariable Long id) {
        logroService.deleteLogro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<LogroEntity>> getAllLogros() {
        List<LogroEntity> logros = logroService.getAllLogros();
        return new ResponseEntity<>(logros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogroEntity> getLogroById(@PathVariable Long id) {
        Optional<LogroEntity> logro = logroService.getLogroById(id);
        return logro.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
