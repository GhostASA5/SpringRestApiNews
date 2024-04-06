package com.example.SpringRestAPI.services.impl;

import com.example.SpringRestAPI.domain.Comment;
import com.example.SpringRestAPI.domain.News;
import com.example.SpringRestAPI.exceptions.EntityNotFoundException;
import com.example.SpringRestAPI.repositories.CommentRepository;
import com.example.SpringRestAPI.services.CommentService;
import com.example.SpringRestAPI.services.NewsService;
import com.example.SpringRestAPI.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final NewsService newsService;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Комментарий с id {0} не найдена", id)));
    }

    @Override
    public Comment save(Comment comment) {
        News excitedNews = newsService.findById(comment.getNews().getId());
        comment.setNews(excitedNews);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        Comment excitedComment = findById(comment.getId());
        News excitedNews = newsService.findById(comment.getNews().getId());
        BeanUtils.copyNonNullProperties(comment, excitedComment);
        excitedComment.setNews(excitedNews);
        return commentRepository.save(excitedComment);

    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

}
