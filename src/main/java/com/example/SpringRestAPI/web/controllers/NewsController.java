package com.example.SpringRestAPI.web.controllers;

import com.example.SpringRestAPI.domain.News;
import com.example.SpringRestAPI.mapper.NewsMapper;
import com.example.SpringRestAPI.services.NewsService;
import com.example.SpringRestAPI.web.dto.news.NewsByIdResponse;
import com.example.SpringRestAPI.web.dto.news.NewsFilterRequest;
import com.example.SpringRestAPI.web.dto.news.NewsListResponse;
import com.example.SpringRestAPI.web.dto.news.NewsRequest;
import jakarta.validation.Valid;
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

    @GetMapping("/filter/{pageNumber}/{pageSize}")
    private ResponseEntity<NewsListResponse> filterBy(@PathVariable Integer pageNumber,
                                                      @PathVariable Integer pageSize,
                                                      @RequestBody NewsFilterRequest filterRequest){
        return ResponseEntity.ok(newsMapper.newsListToResponseList(newsService
                .filterBy(filterRequest, pageNumber, pageSize)));
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    private ResponseEntity<NewsListResponse> findAll(@PathVariable Integer pageNumber, @PathVariable Integer pageSize){
        return ResponseEntity.ok(newsMapper.newsListToResponseList(newsService.findAll(pageNumber, pageSize)));
    }

    @GetMapping("/{id}")
    private ResponseEntity<NewsByIdResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(newsMapper.newsByIdToResponse(newsService.findById(id)));
    }

    @PostMapping
    private ResponseEntity<NewsByIdResponse> create(@RequestBody @Valid NewsRequest request){
        News news = newsService.save(newsMapper.newsRequestToNews(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(newsMapper.newsByIdToResponse(news));
    }

    @PutMapping("/{id}")
    private ResponseEntity<NewsByIdResponse> update(@PathVariable Long id, @RequestBody @Valid NewsRequest request){
        News news = newsService.update(newsMapper.newsRequestToNews(id, request));
        return ResponseEntity.ok(newsMapper.newsByIdToResponse(news));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id){
        newsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
