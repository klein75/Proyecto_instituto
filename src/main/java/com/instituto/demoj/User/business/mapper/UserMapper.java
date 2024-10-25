package com.instituto.demoj.User.business.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.instituto.demoj.User.domain.dto.generalUserDto;
import com.instituto.demoj.User.domain.dto.teacherUserDto;
import com.instituto.demoj.User.domain.entity.User;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // de dto a entidad
    public User toEntity(generalUserDto generalUserDto) {
        return modelMapper.map(generalUserDto, User.class);
    }

    // de entidad a dto
    public generalUserDto toDto(User user) {
        return modelMapper.map(user, generalUserDto.class);
    }

    // de entidad a teacherDto
    public teacherUserDto toTeacherDto(User user) {
        return modelMapper.map(user, teacherUserDto.class);
    }

    // de teacherDto a entidad
    public User toEntity(teacherUserDto teacherUserDto) {
        return modelMapper.map(teacherUserDto, User.class);
    }

    public void updateEntityFromDto(generalUserDto dto, User user) {
        modelMapper.map(dto, user);
    }
}
