package com.example.SpringRestAPI.services;

import com.example.SpringRestAPI.domain.News;

import java.util.List;

public interface NewsService {

    List<News> findAll();

    News findById(Long id);

    News save(News news);

    News update(News news);

    void deleteById(Long id);
}
