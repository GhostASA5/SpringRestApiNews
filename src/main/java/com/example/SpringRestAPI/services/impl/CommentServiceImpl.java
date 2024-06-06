package com.example.SpringRestAPI.services.impl;

import com.example.SpringRestAPI.domain.Comment;
import com.example.SpringRestAPI.domain.News;
import com.example.SpringRestAPI.domain.User;
import com.example.SpringRestAPI.exceptions.EntityNotFoundException;
import com.example.SpringRestAPI.exceptions.VerificationException;
import com.example.SpringRestAPI.repositories.CommentRepository;
import com.example.SpringRestAPI.services.CommentService;
import com.example.SpringRestAPI.services.NewsService;
import com.example.SpringRestAPI.services.UserService;
import com.example.SpringRestAPI.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final NewsService newsService;
    private final UserService userService;

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
    public Comment update(Comment comment, String userName) {
        User user = userService.findByUsername(userName);
        Comment excitedComment = findById(comment.getId());

        if (!Objects.equals(excitedComment.getUser().getId(), user.getId())) {
            throw new VerificationException(MessageFormat.format("Комментарий {0} вам не принадлежит. Вы можете изменять только свои комментарии.",
                    excitedComment.getComment()));
        }
        BeanUtils.copyNonNullProperties(comment, excitedComment);
        return commentRepository.save(excitedComment);

    }

    @Override
    public void deleteById(Long id, String userName) {
        User user = userService.findByUsername(userName);
        Comment excitedComment = findById(id);

        if (user.hasOnlyRoleUser() && !Objects.equals(excitedComment.getUser().getId(), user.getId())) {
            throw new VerificationException(MessageFormat.format("Комментарий {0} вам не принадлежит. Вы можете изменять только свои комментарии.",
                    excitedComment.getComment()));
        }
        commentRepository.deleteById(id);
    }

}
