package com.irojas.demojwt.OpenCourse.Services.servicesImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.transaction.annotation.Transactional;

import com.irojas.demojwt.OpenCourse.Persistence.Entity.OpenCourseEntity;
import com.irojas.demojwt.OpenCourse.Persistence.Repository.OpenCourseRepository;
import com.irojas.demojwt.OpenCourse.Services.Especification.OpenCourseSpecification;
import com.irojas.demojwt.OpenCourse.Services.interfaces.IOpenCourseService;

import java.util.List;

@Service
@Transactional
public class OpenCourseServiceImpl implements IOpenCourseService {

    @Autowired
    private OpenCourseRepository openCourseRepository;

    @Override
    public OpenCourseEntity create(OpenCourseEntity openCourse) {
        return openCourseRepository.save(openCourse);
        
    }

    @Override
    public OpenCourseEntity update(Long id, OpenCourseEntity openCourse) {
        OpenCourseEntity existingCourse = openCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        existingCourse.setStartDate(openCourse.getStartDate());
        existingCourse.setEndDate(openCourse.getEndDate());
        existingCourse.setYear(openCourse.getYear());
        existingCourse.setSemester(openCourse.getSemester());
        existingCourse.setEnrollmentFee(openCourse.getEnrollmentFee());
        existingCourse.setTotalFee(openCourse.getTotalFee());
        existingCourse.setDescription(openCourse.getDescription());
        existingCourse.setState(openCourse.getState());
        existingCourse.setCourse(openCourse.getCourse());
        existingCourse.setStudyDay(openCourse.getStudyDay());
        existingCourse.setInCharge(openCourse.getInCharge());
        existingCourse.setSubjects(openCourse.getSubjects());
        existingCourse.setSchedules(openCourse.getSchedules());

        return openCourseRepository.save(existingCourse);
    }

    @Override
    public void delete(Long id) {
        openCourseRepository.deleteById(id);
    }

    @Override
    public OpenCourseEntity getById(Long id) {
        return openCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public List<OpenCourseEntity> findAll(OpenCourseSpecification specification) {
        return openCourseRepository.findAll(specification);
    }
}
