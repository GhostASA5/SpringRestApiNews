package com.example.SpringRestAPI.web.controllers;

import com.example.SpringRestAPI.domain.News;
import com.example.SpringRestAPI.mapper.NewsMapper;
import com.example.SpringRestAPI.services.NewsService;
import com.example.SpringRestAPI.web.dto.news.NewsListResponse;
import com.example.SpringRestAPI.web.dto.news.NewsRequest;
import com.example.SpringRestAPI.web.dto.news.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final NewsMapper newsMapper;

    @GetMapping
    private ResponseEntity<NewsListResponse> findAll(){
        return ResponseEntity.ok(newsMapper.newsListToResponseList(newsService.findAll()));
    }

    @GetMapping("/{id}")
    private ResponseEntity<NewsResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(newsMapper.newsToResponse(newsService.findById(id)));
    }

    @PostMapping
    private ResponseEntity<NewsResponse> create(@RequestBody NewsRequest request){
        News news = newsService.save(newsMapper.newsRequestToNews(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(newsMapper.newsToResponse(news));
    }

    @PutMapping("/{id}")
    private ResponseEntity<NewsResponse> update(@PathVariable Long id, @RequestBody NewsRequest request){
        News news = newsService.update(newsMapper.newsRequestToNews(id, request));
        return ResponseEntity.ok(newsMapper.newsToResponse(news));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id){
        newsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
