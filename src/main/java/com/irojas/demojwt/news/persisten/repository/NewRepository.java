package com.irojas.demojwt.news.persisten.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.news.persisten.entity.NewsEntity;

public interface NewRepository extends JpaRepository<NewsEntity, Long> {

}
