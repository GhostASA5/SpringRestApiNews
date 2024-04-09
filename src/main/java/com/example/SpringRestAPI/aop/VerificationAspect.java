package com.example.SpringRestAPI.aop;

import com.example.SpringRestAPI.domain.Comment;
import com.example.SpringRestAPI.domain.News;
import com.example.SpringRestAPI.exceptions.VerificationException;
import com.example.SpringRestAPI.services.CommentService;
import com.example.SpringRestAPI.services.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;


@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class VerificationAspect {

    private static final String HTTP_CLIENT_HEADER = "X-Client-Api-Key";

    private final NewsService newsService;
    private final CommentService commentService;

    @Before("@annotation(AuthorVerification)")
    public void beforeUpdate(JoinPoint joinPoint) {
        log.info("Before execution of: {}", joinPoint.getSignature().getName());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String header = request.getHeader(HTTP_CLIENT_HEADER);
        if (header == null){
            throw new VerificationException("Внесен пустой header в запрос");
        }

        Object[] args = joinPoint.getArgs();
        String serviceType = joinPoint.getSignature().getDeclaringTypeName();
        if (request.getMethod().equals("PUT")){
            if (args[0] instanceof News){
                newsVerify(args[0],  header);
            } else if (args[0] instanceof Comment){
                commentVerify(args[0],  header);
            }
        } else if (request.getMethod().equals("DELETE")){
            if (serviceType.contains("NewsServiceImpl")){
                deleteNewsVerify(args[0],  header);
            } else if (serviceType.contains("CommentServiceImpl")){
                deleteCommentVerify(args[0],  header);
            }
        }
    }

    private void newsVerify(Object object, String headerId){
        News news = (News) object;
        Long userId = news.getUser().getId();
        if (userId != Long.parseLong(headerId)){
            throw new VerificationException("Данная новость создана не вами. Пользователь может изменять только свои новости");
        }
    }

    private void commentVerify(Object object, String headerId){
        Comment comment = (Comment) object;
        Long userId = comment.getUser().getId();
        if (userId != Long.parseLong(headerId)){
            throw new VerificationException("Данный комментарий создан не вами. Пользователь может изменять только свои комментарии");
        }
    }

    private void deleteNewsVerify(Object object, String headerId){
        Long id = Long.parseLong(headerId);
        Long newsId = (Long) object;
        News news = newsService.findById(newsId);
        if (!Objects.equals(news.getUser().getId(), id)) {
            throw new VerificationException("Данная новость создана не вами. Пользователь может удалять только свои новости");
        }
    }

    private void deleteCommentVerify(Object object, String headerId){
        Long id = Long.parseLong(headerId);
        Long commentId = (Long) object;
        Comment comment = commentService.findById(commentId);
        if (!Objects.equals(comment.getUser().getId(), id)) {
            throw new VerificationException("Данный комментарий создан не вами. Пользователь может удалять только свои комментарии");
        }
    }
}
