package com.irojas.demojwt.Courses.Services.Interfaces;

import java.util.List;
import java.util.Optional;

import com.irojas.demojwt.Courses.Persistence.Entity.CourseEntity;

public interface IServicesCourse {

    CourseEntity saveCourse(CourseEntity course);
    Optional<CourseEntity> getCourseById(Long id);
    Optional<CourseEntity> getCourseByName(String Name);
    Optional<CourseEntity> getCourseByDescription(String description);
  
    Optional<CourseEntity> getCourseByType(String type);
    List<CourseEntity> getAllCourses();
    CourseEntity updateCourse(Long id, CourseEntity course);
    void deleteCourse(Long id);

}
