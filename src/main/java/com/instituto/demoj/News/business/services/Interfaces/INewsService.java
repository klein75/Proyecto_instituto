package com.instituto.demoj.News.business.services.Interfaces;

import com.instituto.demoj.News.Utils.StateEnum;
import com.instituto.demoj.News.domain.dto.NewsDto;
import com.instituto.demoj.User.Utils.ResponseMessage.ResponseMessage;


public interface INewsService {

     ResponseMessage createNews(NewsDto newsDto);

    
    ResponseMessage updateNews(Long id, NewsDto newsDto);

    
    ResponseMessage deleteNews(Long id);

    
    ResponseMessage getAllNews();

    
    ResponseMessage getNewsById(Long id);

    
    ResponseMessage getNewsByState(StateEnum state);

}
