package com.example.SpringRestAPI.services;

import com.example.SpringRestAPI.domain.News;
import com.example.SpringRestAPI.web.dto.news.NewsFilterRequest;

import java.util.List;

public interface NewsService {

    List<News> findAll(Integer page, Integer size);

    News findById(Long id);

    News save(News news);

    News update(News news);

    void deleteById(Long id);

    List<News> filterBy(NewsFilterRequest request, Integer page, Integer size);
}
