package com.irojas.demojwt.studentDay.presentation.controller;

import java.util.List;

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

import com.irojas.demojwt.Utilities.Enum.StateEnum;
import com.irojas.demojwt.studentDay.Services.interfaces.IStudyDayServices;
import com.irojas.demojwt.studentDay.presentation.DTO.StudyDayDto;

@RestController
@RequestMapping("/api/study-days")
public class StudyDayController {

    @Autowired
    private IStudyDayServices studyDayServices;

    // Endpoint para crear un nuevo StudyDay
    @PostMapping("/create")
    public ResponseEntity<StudyDayDto> createStudyDay(@RequestBody StudyDayDto studyDayDto) {
        StudyDayDto createdStudyDay = studyDayServices.saveStudyDay(studyDayDto);
        return new ResponseEntity<>(createdStudyDay, HttpStatus.CREATED);
    }

    // Endpoint para obtener un StudyDay por su ID
    @GetMapping("/{id}")
    public ResponseEntity<StudyDayDto> getStudyDayById(@PathVariable Long id) {
        return studyDayServices.findById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para obtener un StudyDay por su jornada
    @GetMapping("/jornada/{studyDay}")
    public ResponseEntity<StudyDayDto> getStudyDayByStudyDay(@PathVariable String studyDay) {
        return studyDayServices.findByStudyDay(studyDay)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para obtener un StudyDay por su estado
    @GetMapping("/estado/{state}")
    public ResponseEntity<StudyDayDto> getStudyDayByState(@PathVariable StateEnum state) {
        return studyDayServices.findByState(state)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para obtener todos los StudyDays
    @GetMapping("/all")
    public ResponseEntity<List<StudyDayDto>> getAllStudyDays() {
        List<StudyDayDto> studyDays = studyDayServices.findAllStudyDay();
        return new ResponseEntity<>(studyDays, HttpStatus.OK);
    }

    // Endpoint para actualizar un StudyDay
    @PutMapping("actualizar/{id}")
    public ResponseEntity<StudyDayDto> updateStudyDay(
            @PathVariable Long id,
            @RequestBody StudyDayDto studyDayDto) {

        StudyDayDto updatedStudyDay = studyDayServices.updateStudyDay(id, studyDayDto);
        return new ResponseEntity<>(updatedStudyDay, HttpStatus.OK);
    }

    // Endpoint para eliminar un StudyDay
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> deleteStudyDay(@PathVariable Long id) {
        studyDayServices.deleteStudyDay(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


