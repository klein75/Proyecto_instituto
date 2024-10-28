package com.instituto.demoj.News.business.services.ServicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.instituto.demoj.News.Utils.StateEnum;
import com.instituto.demoj.News.business.mapper.NewsMapper;
import com.instituto.demoj.News.business.services.Interfaces.INewsService;
import com.instituto.demoj.News.domain.dto.NewsDto;
import com.instituto.demoj.News.domain.entity.NewsEntity;
import com.instituto.demoj.News.persistence.NewsRepository;
import com.instituto.demoj.User.Utils.ResponseMessage.ResponseMessage;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class NewsServiceImpl implements INewsService {

     private final NewsRepository newsRepository;
    private final Validator validator;
    private final NewsMapper newsMapper;

    public NewsServiceImpl(NewsRepository newsRepository, Validator validator, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.validator = validator;
        this.newsMapper = newsMapper;
    }

    private String validateDto(NewsDto newsDto) {
        Set<ConstraintViolation<NewsDto>> violations = validator.validate(newsDto);
        if (!violations.isEmpty()) {
            return violations.stream()
                    .map(v -> "Campo: " + v.getPropertyPath() + " - Error: " + v.getMessage())
                    .collect(Collectors.joining("; "));
        }
        return null;
    }

    @Override
    public ResponseMessage createNews(NewsDto newsDto) {
        String validationErrors = validateDto(newsDto);
        if (validationErrors != null) {
            return new ResponseMessage(false, validationErrors, null);
        }

        try {
            NewsDto savedNews = newsMapper.toDto(newsRepository.save(newsMapper.toEntity(newsDto)));
            return new ResponseMessage(true, "Noticia creada correctamente", savedNews);
        } catch (Exception e) {
            return new ResponseMessage(false, "Error al crear la noticia: " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseMessage updateNews(Long id, NewsDto newsDto) {
        String validationErrors = validateDto(newsDto);
        if (validationErrors != null) {
            return new ResponseMessage(false, validationErrors, null);
        }

        Optional<NewsEntity> optionalNews = newsRepository.findById(id);
        if (optionalNews.isEmpty()) {
            return new ResponseMessage(false, "La noticia con ID " + id + " no fue encontrada", null);
        }

        try {
            NewsEntity newsToUpdate = newsMapper.toEntity(newsDto);
            newsToUpdate.setId(id); // Asigna el ID de la noticia existente
            NewsDto updatedNews = newsMapper.toDto(newsRepository.save(newsToUpdate));
            return new ResponseMessage(true, "Noticia actualizada correctamente", updatedNews);
        } catch (Exception e) {
            return new ResponseMessage(false, "Error al actualizar la noticia: " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseMessage deleteNews(Long id) {
        if (!newsRepository.existsById(id)) {
            return new ResponseMessage(false, "La noticia con ID " + id + " no fue encontrada", null);
        }

        try {
            newsRepository.deleteById(id);
            return new ResponseMessage(true, "La noticia con ID " + id + " fue eliminada correctamente", null);
        } catch (Exception e) {
            return new ResponseMessage(false, "Error al eliminar la noticia: " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseMessage getAllNews() {
        List<NewsEntity> newsList = newsRepository.findAll();
        if (newsList.isEmpty()) {
            return new ResponseMessage(false, "No se encontraron noticias", null);
        }

        List<NewsDto> newsDtoList = newsList.stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseMessage(true, "Noticias obtenidas exitosamente", newsDtoList);
    }

    @Override
    public ResponseMessage getNewsById(Long id) {
        Optional<NewsEntity> optionalNews = newsRepository.findById(id);
        if (optionalNews.isEmpty()) {
            return new ResponseMessage(false, "La noticia con ID " + id + " no fue encontrada", null);
        }

        return new ResponseMessage(true, "Noticia encontrada", newsMapper.toDto(optionalNews.get()));
    }

    @Override
    public ResponseMessage getNewsByState(StateEnum state) {
        List<NewsEntity> newsByState = newsRepository.findByState(state);
        if (newsByState.isEmpty()) {
            return new ResponseMessage(false, "No se encontraron noticias con el estado " + state, null);
        }

        List<NewsDto> newsDtoList = newsByState.stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseMessage(true, "Noticias encontradas con el estado " + state, newsDtoList);
    }

 
}


