package com.instituto.demoj.News.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instituto.demoj.News.Utils.StateEnum;
import com.instituto.demoj.News.domain.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

    List<NewsEntity> findByState(StateEnum state);

}
