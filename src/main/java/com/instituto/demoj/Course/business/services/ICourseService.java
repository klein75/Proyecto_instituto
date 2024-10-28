package com.instituto.demoj.Course.business.services;



import com.instituto.demoj.Course.Domain.Dto.CourseDto;
import com.instituto.demoj.User.Utils.ResponseMessage.ResponseMessage;
import com.instituto.demoj.Utilities.Enum.StateEnum;

public interface ICourseService {


    public ResponseMessage saveCourse(CourseDto courseDto);

    public ResponseMessage updateCourse(Long id, CourseDto courseDto);

    public ResponseMessage deleteCourse(Long id);

    public ResponseMessage getCourses();

    public ResponseMessage getCourseById(Long id);

    public ResponseMessage getCourseByState(StateEnum state);

}
