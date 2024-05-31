package com.example.SpringRestAPI.mapper.delegates;

import com.example.SpringRestAPI.domain.News;
import com.example.SpringRestAPI.mapper.NewsMapper;
import com.example.SpringRestAPI.services.NewsCategoryService;
import com.example.SpringRestAPI.services.UserService;
import com.example.SpringRestAPI.web.dto.news.NewsRequest;
import com.example.SpringRestAPI.web.dto.news.NewsResponse;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class NewsMapperDelegate implements NewsMapper {


    @Autowired
    private UserService userService;

    @Autowired
    private NewsCategoryService categoryService;


    @Override
    public News newsRequestToNews(NewsRequest request) {
        News news = new News();
        news.setDescription(request.getDescription());
        news.setUser(userService.findById(request.getUserId()));
        news.setCategory(categoryService.findById(request.getCategoryId()));
        return news;
    }

    @Override
    public News newsRequestToNews(Long newsId, NewsRequest request) {
        News news = newsRequestToNews(request);
        news.setId(newsId);
        return news;
    }

    @Override
    public NewsResponse newsToResponse(News news) {
        NewsResponse newsResponse = new NewsResponse();
        newsResponse.setId(news.getId());
        newsResponse.setNewsCategory(news.getCategory().getCategory());
        newsResponse.setDescription(news.getDescription());
        newsResponse.setUserName(news.getUser().getUsername());
        newsResponse.setCommentsCount(news.getComments().size());
        return newsResponse;
    }
}
