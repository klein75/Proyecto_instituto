package com.instituto.demoj.Course.presentation;

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

import com.instituto.demoj.Course.Domain.Dto.CourseDto;
import com.instituto.demoj.Course.business.facade.CourseFacade;
import com.instituto.demoj.User.Utils.ResponseMessage.ResponseMessage;
import com.instituto.demoj.Utilities.Enum.StateEnum;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseFacade courseFacade;

    @Autowired
    public CourseController(CourseFacade courseFacade) {
        this.courseFacade = courseFacade;
    }
    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createCourse(@RequestBody CourseDto courseDto) {
        return courseFacade.saveCourse(courseDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseMessage> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        return courseFacade.updateCourse(id, courseDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteCourse(@PathVariable Long id) {
        return courseFacade.deleteCourse(id);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseMessage> getAllCourses() {
        return courseFacade.getCourses();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseMessage> getCourseById(@PathVariable Long id) {
        return courseFacade.getCourseById(id);
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<ResponseMessage> getCoursesByState(@PathVariable StateEnum state) {
        return courseFacade.getCourseByState(state);
    }
}


