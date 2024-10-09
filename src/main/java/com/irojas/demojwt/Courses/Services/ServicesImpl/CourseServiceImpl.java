package com.irojas.demojwt.Courses.Services.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Courses.Persistence.Entity.CourseEntity;
import com.irojas.demojwt.Courses.Persistence.Repositoty.CourseRepository;
import com.irojas.demojwt.Courses.Services.Interfaces.IServicesCourse;

@Service 
public class CourseServiceImpl implements IServicesCourse {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public CourseEntity saveCourse(CourseEntity course) {
      return courseRepository.save(course);
    }

    @Override
    public Optional<CourseEntity> getCourseById(Long id) {
      return courseRepository.findById(id);
    }

    @Override
    public Optional<CourseEntity> getCourseByName(String Name) {
        return courseRepository.findByName(Name);
    }

    @Override
    public Optional<CourseEntity> getCourseByDescription(String description) {
        return courseRepository.findByDescription(description);
    }


    @Override
    public Optional<CourseEntity> getCourseByType(String type) {
        return courseRepository.findByType(type);
    }

    @Override
    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public CourseEntity updateCourse(Long id, CourseEntity course) {
        Optional<CourseEntity> existingCouse = courseRepository.findById(id);
        if(existingCouse.isPresent()) {
            CourseEntity updatedCourse = existingCouse.get();
            updatedCourse.setName(course.getName());
            updatedCourse.setDescription(course.getDescription());
            updatedCourse.setType(course.getType());
            updatedCourse.setState(course.getState());
            return courseRepository.save(updatedCourse);}
        else{
            throw new RuntimeException("Course not found with id " + id);
        }
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}
