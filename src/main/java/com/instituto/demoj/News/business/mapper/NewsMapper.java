package com.instituto.demoj.News.business.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.instituto.demoj.News.domain.dto.NewsDto;
import com.instituto.demoj.News.domain.entity.NewsEntity;

@Component
public class NewsMapper {

    private final ModelMapper modelMapper;

    public NewsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // de dto a entidad
    public NewsEntity toEntity(NewsDto dto) {
        return modelMapper.map(dto, NewsEntity.class);
    }

    // de entidad a dto
    public NewsDto toDto(NewsEntity entity) {    
        return modelMapper.map(entity, NewsDto.class);
    }
    

}
