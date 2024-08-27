package com.irojas.demojwt.Subject.Services.Interfaces;

import java.util.List;
import java.util.Optional;

import com.irojas.demojwt.Subject.Persistence.Entity.SubjectEntity;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

public interface ISubjectService {

    SubjectEntity saveSubject(SubjectEntity subject);

    Optional<SubjectEntity> findBySubject(String subject);

    SubjectEntity findByDescription(String description);

    SubjectEntity findByState(StateEnum state);

    Optional<SubjectEntity> findById(Long id);

    List<SubjectEntity> getAllSubjects();

    SubjectEntity updateSubject(Long id, SubjectEntity subject);

    void deleteSubject(Long id);
    SubjectEntity addProfessorToSubject(Long subjectId, Long userId);
    
    SubjectEntity removeProfessorFromSubject(Long subjectId, Long userId);

}
