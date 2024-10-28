package com.instituto.demoj.News.business.facade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.instituto.demoj.News.Utils.StateEnum;
import com.instituto.demoj.News.business.services.Interfaces.INewsService;
import com.instituto.demoj.News.domain.dto.NewsDto;
import com.instituto.demoj.User.Utils.ResponseMessage.ResponseMessage;

@Component
public class NewsFacade {
    private final INewsService newsService;

    public NewsFacade(INewsService newsService) {
        this.newsService = newsService;
    }

    public ResponseEntity<?> createNews(NewsDto newsDto) {
        ResponseMessage response = newsService.createNews(newsDto);
        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> updateNews(Long id, NewsDto newsDto) {
        ResponseMessage response = newsService.updateNews(id, newsDto);
        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteNews(Long id) {
        ResponseMessage response = newsService.deleteNews(id);
        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getAllNews() {
        ResponseMessage response = newsService.getAllNews();
        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getNewsById(Long id) {
        ResponseMessage response = newsService.getNewsById(id);
        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getNewsByState(StateEnum state) {
        ResponseMessage response = newsService.getNewsByState(state);
        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}


