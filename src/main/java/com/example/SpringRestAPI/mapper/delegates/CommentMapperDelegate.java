package com.example.SpringRestAPI.mapper.delegates;

import com.example.SpringRestAPI.domain.Comment;
import com.example.SpringRestAPI.mapper.CommentMapper;
import com.example.SpringRestAPI.services.CommentService;
import com.example.SpringRestAPI.services.NewsService;
import com.example.SpringRestAPI.services.UserService;
import com.example.SpringRestAPI.web.dto.comment.CommentRequestCreate;
import com.example.SpringRestAPI.web.dto.comment.CommentRequestUpdate;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommentMapperDelegate implements CommentMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;
    @Autowired
    private CommentService commentService;


    @Override
    public Comment commentRequestToComment(CommentRequestCreate request) {
        Comment comment = new Comment();
        comment.setComment(request.getComment());
        comment.setUser(userService.findById(request.getUserId()));
        comment.setNews(newsService.findById(request.getNewsId()));
        return comment;
    }

    @Override
    public Comment commentRequestUpdateToComment(Long commentId, CommentRequestUpdate request) {
        Comment comment = commentService.findById(commentId);
        comment.setComment(request.getComment());
        return comment;
    }
}
