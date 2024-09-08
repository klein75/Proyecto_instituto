package com.irojas.demojwt.Subject.Presentation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irojas.demojwt.Subject.Persistence.Entity.SubjectEntity;
import com.irojas.demojwt.Subject.Services.Interfaces.ISubjectService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @PostMapping("/create")
    public ResponseEntity<SubjectEntity> saveSubject(@RequestBody SubjectEntity subject) {
        SubjectEntity saveSubject = subjectService.saveSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveSubject);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SubjectEntity> findById(@RequestBody Long id) {
        Optional<SubjectEntity> subject = subjectService.findById(id);
        return subject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public BodyBuilder getAllSubjects() {
        List<SubjectEntity> subjects = subjectService.getAllSubjects();
        return (BodyBuilder) ResponseEntity.status(HttpStatus.OK).body(subjects);
    }

    @GetMapping("/find/subject")
    public ResponseEntity<Optional<SubjectEntity>> findBySubject(@RequestBody SubjectEntity subject) {
        Optional<SubjectEntity> findBySubject = subjectService.findBySubject(subject.getSubject());
        return ResponseEntity.status(HttpStatus.OK).body(findBySubject);

    }

    @GetMapping("/find/description")
    public ResponseEntity<SubjectEntity> findByDescription(@RequestBody SubjectEntity subject) {
        SubjectEntity findByDescription = subjectService.findByDescription(subject.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(findByDescription);
    }

    @GetMapping("/find/state")
    public ResponseEntity<SubjectEntity> findByState(@RequestBody SubjectEntity subject) {
        SubjectEntity findByState = subjectService.findByState(subject.getState());
        return ResponseEntity.status(HttpStatus.OK).body(findByState);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SubjectEntity> updateSubject(@RequestBody SubjectEntity subject) {
        SubjectEntity updateSubject = subjectService.updateSubject(subject.getId(), subject);
        return ResponseEntity.status(HttpStatus.OK).body(updateSubject);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SubjectEntity> deleteSubject(@RequestBody Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

     @PostMapping("/{subjectId}/professors/{userId}")
    public ResponseEntity<SubjectEntity> addProfessorToSubject(@PathVariable Long subjectId, @PathVariable Long userId) {
        return ResponseEntity.ok(subjectService.addProfessorToSubject(subjectId, userId));
    }

    @DeleteMapping("/{subjectId}/professors/{userId}")
    public ResponseEntity<SubjectEntity> removeProfessorFromSubject(@PathVariable Long subjectId, @PathVariable Long userId) {
        return ResponseEntity.ok(subjectService.removeProfessorFromSubject(subjectId, userId));
    }

}
