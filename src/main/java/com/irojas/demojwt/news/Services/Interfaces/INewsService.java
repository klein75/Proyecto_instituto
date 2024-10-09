package com.irojas.demojwt.news.Services.Interfaces;

import java.util.List;

import com.irojas.demojwt.news.presentation.Dto.NewsDto;

public interface INewsService {
    List<NewsDto> getAllNews();
    NewsDto getNewsById(Long id);
    NewsDto createNews(NewsDto newsDto);
    NewsDto updateNews(Long id, NewsDto newsDto);
    void deleteNews(Long id);

}
