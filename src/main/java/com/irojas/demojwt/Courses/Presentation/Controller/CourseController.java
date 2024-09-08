package com.irojas.demojwt.Courses.Presentation.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irojas.demojwt.Courses.Persistence.Entity.CourseEntity;
import com.irojas.demojwt.Courses.Services.Interfaces.IServicesCourse;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private IServicesCourse courseService;

    @PostMapping("/create")
    public ResponseEntity<CourseEntity> createCourse(@RequestBody CourseEntity course) {
        CourseEntity savedCourse = courseService.saveCourse(course);

        return ResponseEntity.ok(savedCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEntity> getCourseById(@PathVariable Long id) {
        Optional<CourseEntity> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{course}")
    public ResponseEntity<CourseEntity> getCourseByCourse(@PathVariable String course) {
        Optional<CourseEntity> courseEntity = courseService.getCourseByCourse(course);
        return courseEntity.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<CourseEntity> getCourseByDescription(@PathVariable String description) {
        Optional<CourseEntity> courseEntity = courseService.getCourseByDescription(description);
        return courseEntity.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cycle/{cycle}")
    public ResponseEntity<CourseEntity> getCourseByCycle(@PathVariable String cycle) {
        Optional<CourseEntity> courseEntity = courseService.getCourseByCycle(cycle);
        return courseEntity.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<CourseEntity> getCourseByType(@PathVariable String type) {
        Optional<CourseEntity> courseEntity = courseService.getCourseByType(type);
        return courseEntity.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseEntity>> getAllCourses() {
        List<CourseEntity> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);}

    @PutMapping("/update/{id}")
    public ResponseEntity<CourseEntity> updateCourse(@PathVariable Long id, @RequestBody CourseEntity course) {
        CourseEntity updatedCourse = courseService.updateCourse(id, course);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
