package com.example.SpringRestAPI.mapper.delegates;

import com.example.SpringRestAPI.domain.Comment;
import com.example.SpringRestAPI.mapper.CommentMapper;
import com.example.SpringRestAPI.services.NewsService;
import com.example.SpringRestAPI.services.UserService;
import com.example.SpringRestAPI.web.dto.comment.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommentMapperDelegate implements CommentMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;


    @Override
    public Comment commentRequestToComment(CommentRequest request) {
        Comment comment = new Comment();
        comment.setComment(request.getComment());
        comment.setUser(userService.findById(request.getUserId()));
        comment.setNews(newsService.findById(request.getNewsId()));
        return comment;
    }

    @Override
    public Comment commentRequestToComment(Long commentId, CommentRequest request) {
        Comment comment = commentRequestToComment(request);
        comment.setId(commentId);
        return comment;
    }
}
