package com.example.SpringRestAPI.services;

import com.example.SpringRestAPI.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    Comment findById(Long id);

    Comment save(Comment comment);

    Comment update(Comment comment, String userName);

    void deleteById(Long id, String userName);

}
