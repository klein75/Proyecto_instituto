package com.irojas.demojwt.OpenCourse.Persistence.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.irojas.demojwt.OpenCourse.Persistence.Entity.OpenCourseEntity;

public interface OpenCourseRepository extends JpaRepository<OpenCourseEntity, Long>, JpaSpecificationExecutor<OpenCourseEntity>  {

}
