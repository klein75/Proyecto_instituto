package com.instituto.demoj.Course.business.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.instituto.demoj.Course.Domain.Dto.CourseDto;
import com.instituto.demoj.Course.Domain.Entity.CoursesEntity;

@Component
public class CourseMapper {

    private final ModelMapper modelMapper;

    public CourseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // de dto a entidad
    public CoursesEntity toEntity(CourseDto courseDto) {
        return modelMapper.map(courseDto, CoursesEntity.class);
    }

    // de entidad a dto
    public CourseDto toDto(CoursesEntity coursesEntity) {
        return modelMapper.map(coursesEntity, CourseDto.class);
    }
    

}
