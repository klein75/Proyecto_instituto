package com.irojas.demojwt.OpenCourse.Presentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.irojas.demojwt.OpenCourse.Persistence.Entity.OpenCourseEntity;
import com.irojas.demojwt.OpenCourse.Services.Especification.OpenCourseSpecification;
import com.irojas.demojwt.OpenCourse.Services.interfaces.IOpenCourseService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/open-courses")
public class OpenCourseController {

    @Autowired
    private IOpenCourseService openCourseService;

    @PostMapping("all")
    public ResponseEntity<OpenCourseEntity> createOpenCourse(@RequestBody OpenCourseEntity openCourse) {
        OpenCourseEntity createdCourse = openCourseService.create(openCourse);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<OpenCourseEntity> updateOpenCourse(@PathVariable Long id, @RequestBody OpenCourseEntity openCourse) {
        OpenCourseEntity updatedCourse = openCourseService.update(id, openCourse);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> deleteOpenCourse(@PathVariable Long id) {
        openCourseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpenCourseEntity> getOpenCourseById(@PathVariable Long id) {
        OpenCourseEntity openCourse = openCourseService.getById(id);
        return new ResponseEntity<>(openCourse, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<OpenCourseEntity>> searchOpenCourses(OpenCourseSpecification specification) {
        List<OpenCourseEntity> openCourses = openCourseService.findAll(specification);
        return new ResponseEntity<>(openCourses, HttpStatus.OK);
    }
}
