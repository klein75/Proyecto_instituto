package com.instituto.demoj.Course.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instituto.demoj.Course.Domain.Entity.CoursesEntity;
import com.instituto.demoj.Utilities.Enum.StateEnum;

public interface CoursesRepository extends JpaRepository<CoursesEntity,Long> {

    List<CoursesEntity> findByState(StateEnum state);

}
