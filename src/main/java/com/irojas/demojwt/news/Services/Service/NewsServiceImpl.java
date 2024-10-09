package com.irojas.demojwt.news.Services.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.User.Persisten.Repository.UserRepository;
import com.irojas.demojwt.User.Persisten.entity.User;
import com.irojas.demojwt.news.Services.Interfaces.INewsService;
import com.irojas.demojwt.news.persisten.entity.NewsEntity;
import com.irojas.demojwt.news.persisten.repository.NewRepository;
import com.irojas.demojwt.news.presentation.Dto.NewsDto;
@Service
public class NewsServiceImpl  implements INewsService{

    @Autowired
    private NewRepository newsRepository;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<NewsDto> getAllNews() {
        List<NewsEntity> newsList = newsRepository.findAll();
        return newsList.stream()
                .map(news -> modelMapper.map(news, NewsDto.class))  
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto getNewsById(Long id) {
        NewsEntity newsEntity = newsRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("News not found"));
        return modelMapper.map(newsEntity, NewsDto.class); 
    }

    @Override
    public NewsDto createNews(NewsDto newsDto) {
        NewsEntity newsEntity = modelMapper.map(newsDto, NewsEntity.class);  
        newsEntity.setFechaCreacion(LocalDateTime.now());

        User autor = userRepository.findById(newsDto.getAutorId())
            .orElseThrow(() -> new RuntimeException("Autor not found"));
        newsEntity.setAutor(autor);

        NewsEntity savedNews = newsRepository.save(newsEntity);
        return modelMapper.map(savedNews, NewsDto.class);  
    }

    @Override
    public NewsDto updateNews(Long id, NewsDto newsDto) {
        NewsEntity existingNews = newsRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("News not found"));

        modelMapper.map(newsDto, existingNews);  
        existingNews.setFechaCreacion(existingNews.getFechaCreacion()); 

        User autor = userRepository.findById(newsDto.getAutorId())
            .orElseThrow(() -> new RuntimeException("Autor not found"));
        existingNews.setAutor(autor);

        NewsEntity updatedNews = newsRepository.save(existingNews);
        return modelMapper.map(updatedNews, NewsDto.class);  
    }

    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}



