package com.instituto.demoj.News.Presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instituto.demoj.News.Utils.StateEnum;
import com.instituto.demoj.News.business.facade.NewsFacade;
import com.instituto.demoj.News.domain.dto.NewsDto;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/news")
public class NewsController {

      private final NewsFacade newsFacade;

    public NewsController(NewsFacade newsFacade) {
        this.newsFacade = newsFacade;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNews(@Valid @RequestBody NewsDto newsDto) {
        return newsFacade.createNews(newsDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNews(@Valid@PathVariable Long id, @RequestBody NewsDto newsDto) {
        return newsFacade.updateNews(id, newsDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id) {
        return newsFacade.deleteNews(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllNews() {
        return newsFacade.getAllNews();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable Long id) {
        return newsFacade.getNewsById(id);
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<?> getNewsByState(@PathVariable StateEnum state) {
        return newsFacade.getNewsByState(state);
    }
}


