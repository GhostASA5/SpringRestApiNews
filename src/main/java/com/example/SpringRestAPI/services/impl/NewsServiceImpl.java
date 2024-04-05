package com.example.SpringRestAPI.services.impl;

import com.example.SpringRestAPI.domain.News;
import com.example.SpringRestAPI.domain.NewsCategory;
import com.example.SpringRestAPI.exceptions.EntityNotFoundException;
import com.example.SpringRestAPI.repositories.NewsRepository;
import com.example.SpringRestAPI.services.NewsCategoryService;
import com.example.SpringRestAPI.services.NewsService;
import com.example.SpringRestAPI.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsCategoryService categoryService;

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Новость с id {0} не найдена", id)));
    }

    @Override
    public News save(News news) {
        NewsCategory excitedCategory = categoryService.findById(news.getCategory().getId());
        news.setCategory(excitedCategory);
        return newsRepository.save(news);
    }

    @Override
    public News update(News news) {
        News excitedNews = findById(news.getId());
        NewsCategory excitedCategory = categoryService.findById(news.getId());
        BeanUtils.copyNonNullProperties(news, excitedNews);
        excitedNews.setCategory(excitedCategory);
        return newsRepository.save(excitedNews);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

}
