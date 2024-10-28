package com.instituto.demoj.Course.business.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.instituto.demoj.Course.Domain.Dto.CourseDto;
import com.instituto.demoj.Course.business.services.ICourseService;
import com.instituto.demoj.User.Utils.ResponseMessage.ResponseMessage;
import com.instituto.demoj.Utilities.Enum.StateEnum;

@Component
public class CourseFacade {

    private final ICourseService courseService;

    @Autowired
    public CourseFacade(ICourseService courseService) {
        this.courseService = courseService;
    }

    public ResponseEntity<ResponseMessage> saveCourse(CourseDto courseDto) {
        ResponseMessage response = courseService.saveCourse(courseDto);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ResponseMessage> updateCourse(Long id, CourseDto courseDto) {
        ResponseMessage response = courseService.updateCourse(id, courseDto);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ResponseMessage> deleteCourse(Long id) {
        ResponseMessage response = courseService.deleteCourse(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ResponseMessage> getCourses() {
        ResponseMessage response = courseService.getCourses();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ResponseMessage> getCourseById(Long id) {
        ResponseMessage response = courseService.getCourseById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ResponseMessage> getCourseByState(StateEnum state) {
        ResponseMessage response = courseService.getCourseByState(state);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
