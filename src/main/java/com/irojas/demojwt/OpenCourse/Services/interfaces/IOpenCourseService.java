package com.irojas.demojwt.OpenCourse.Services.interfaces;
import java.util.List;

import com.irojas.demojwt.OpenCourse.Persistence.Entity.OpenCourseEntity;
import com.irojas.demojwt.OpenCourse.Services.Especification.OpenCourseSpecification;

public interface IOpenCourseService {

    OpenCourseEntity create(OpenCourseEntity openCourse);

    OpenCourseEntity update(Long id, OpenCourseEntity openCourse);

    void delete(Long id);

    OpenCourseEntity getById(Long id);

    List<OpenCourseEntity> findAll(OpenCourseSpecification specification);

}
