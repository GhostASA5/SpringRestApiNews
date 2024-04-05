package com.example.SpringRestAPI.web.controllers;

import com.example.SpringRestAPI.domain.Comment;
import com.example.SpringRestAPI.domain.NewsCategory;
import com.example.SpringRestAPI.mapper.NewsCategoryMapper;
import com.example.SpringRestAPI.services.NewsCategoryService;
import com.example.SpringRestAPI.web.dto.comment.CommentListResponse;
import com.example.SpringRestAPI.web.dto.comment.CommentRequest;
import com.example.SpringRestAPI.web.dto.comment.CommentResponse;
import com.example.SpringRestAPI.web.dto.newscategories.NewsCategoryListResponse;
import com.example.SpringRestAPI.web.dto.newscategories.NewsCategoryRequest;
import com.example.SpringRestAPI.web.dto.newscategories.NewsCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news-categories")
@RequiredArgsConstructor
public class NewsCategoryController {

    private final NewsCategoryService newsCategoryService;
    private final NewsCategoryMapper categoryMapper;

    @GetMapping
    private ResponseEntity<NewsCategoryListResponse> findAll(){
        return ResponseEntity.ok(categoryMapper.categoryListToResponseList(newsCategoryService.findAll()));
    }

    @GetMapping("/{id}")
    private ResponseEntity<NewsCategoryResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoryMapper.categoryToResponse(newsCategoryService.findById(id)));
    }

    @PostMapping()
    private ResponseEntity<NewsCategoryResponse> create(@RequestBody NewsCategoryRequest request){
        NewsCategory category = newsCategoryService.save(categoryMapper.categoryRequestToCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.categoryToResponse(category));
    }

    @PutMapping("/{id}")
    private ResponseEntity<NewsCategoryResponse> update(@PathVariable Long id, @RequestBody NewsCategoryRequest request){
        NewsCategory category = newsCategoryService.update(categoryMapper.categoryRequestToCategory(id, request));
        return ResponseEntity.ok(categoryMapper.categoryToResponse(category));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id){
        newsCategoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
