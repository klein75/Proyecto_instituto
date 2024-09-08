package com.irojas.demojwt.Courses.Persistence.Repositoty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Courses.Persistence.Entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

	Optional<CourseEntity> findByCourse(String course);

    Optional<CourseEntity> findByDescription(String description);

    Optional<CourseEntity> findByType(String type);

}
