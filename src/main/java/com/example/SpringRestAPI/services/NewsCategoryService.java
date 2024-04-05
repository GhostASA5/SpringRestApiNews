package com.example.SpringRestAPI.services;

import com.example.SpringRestAPI.domain.NewsCategory;

import java.util.List;

public interface NewsCategoryService {

    List<NewsCategory> findAll();

    NewsCategory findById(Long id);

    NewsCategory save(NewsCategory category);

    NewsCategory update(NewsCategory category);

    void deleteById(Long id);
}
