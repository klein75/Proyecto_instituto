package com.irojas.demojwt.Subject.Services.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Subject.Persistence.Entity.SubjectEntity;
import com.irojas.demojwt.Subject.Persistence.Respository.SubjectRespository;
import com.irojas.demojwt.Subject.Services.Interfaces.ISubjectService;
import com.irojas.demojwt.User.Persisten.Repository.UserRepository;
import com.irojas.demojwt.User.Persisten.entity.User;
import com.irojas.demojwt.Utilities.Enum.RoleEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

@Service
public class SubjectServiceImpl implements ISubjectService {

    @Autowired
    SubjectRespository subjectRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public SubjectEntity saveSubject(SubjectEntity subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Optional<SubjectEntity> findBySubject(String subject) {
        return subjectRepository.findBySubject(subject);
    }

    @Override
    public SubjectEntity findByDescription(String description) {
        return subjectRepository.findByDescription(description);
    }

    @Override
    public SubjectEntity findByState(StateEnum state) {
        return subjectRepository.findByState(state);
    }

    @Override
    public Optional<SubjectEntity> findById(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public List<SubjectEntity> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public SubjectEntity updateSubject(Long id, SubjectEntity subject) {
       Optional<SubjectEntity> subjectEntity = subjectRepository.findById(id);
       if(subjectEntity.isPresent()) {
           subjectEntity.get().setSubject(subject.getSubject());
           subjectEntity.get().setDescription(subject.getDescription());
           subjectEntity.get().setState(subject.getState());
           return subjectRepository.save(subjectEntity.get());
       } else {
        throw new RuntimeException("Subject not found with id " + id);
       }
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
public SubjectEntity addProfessorToSubject(Long subjectId, Long userId) {
    SubjectEntity subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new RuntimeException("Subject not found"));

    User docente = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    // Verificar si el usuario tiene el rol de DOCENTE
    boolean isDocente = docente.getRoles().stream()
            .anyMatch(role -> role.getRol().equals(RoleEnum.DOCENTE));

    if (!isDocente) {
        throw new RuntimeException("User is not a DOCENTE");
    }

    subject.getDocente().add(docente);
    return subjectRepository.save(subject);
}
    @Override
    public SubjectEntity removeProfessorFromSubject(Long subjectId, Long userId) {
        SubjectEntity subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        User docente = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        subject.getDocente().remove(docente);
        return subjectRepository.save(subject);
    }
}


