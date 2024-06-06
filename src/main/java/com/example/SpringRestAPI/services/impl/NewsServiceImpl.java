package com.example.SpringRestAPI.services.impl;

import com.example.SpringRestAPI.domain.News;
import com.example.SpringRestAPI.domain.NewsCategory;
import com.example.SpringRestAPI.domain.User;
import com.example.SpringRestAPI.exceptions.EntityNotFoundException;
import com.example.SpringRestAPI.exceptions.VerificationException;
import com.example.SpringRestAPI.repositories.NewsRepository;
import com.example.SpringRestAPI.repositories.NewsSpecification;
import com.example.SpringRestAPI.services.NewsCategoryService;
import com.example.SpringRestAPI.services.NewsService;
import com.example.SpringRestAPI.services.UserService;
import com.example.SpringRestAPI.utils.BeanUtils;
import com.example.SpringRestAPI.web.dto.news.NewsFilterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final UserService userService;
    private final NewsRepository newsRepository;
    private final NewsCategoryService categoryService;

    @Override
    public List<News> findAll(Integer page, Integer size) {
        return newsRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Новость с id {0} не найдена", id)));
    }

    @Override
    public News save(News news) {
        NewsCategory excitedCategory = categoryService.findById(news.getCategory().getId());
        news.setCategory(excitedCategory);
        return newsRepository.save(news);
    }

    @Override
    public News update(News news, String userName) {
        User user = userService.findByUsername(userName);
        News excitedNews = findById(news.getId());

        if (!Objects.equals(excitedNews.getUser().getId(), user.getId())) {
            throw new VerificationException(MessageFormat.format("Новость {0} вам не принадлежит. Вы можете изменять только свои новости.",
                    excitedNews.getDescription()));
        }
        NewsCategory excitedCategory = categoryService.findById(news.getCategory().getId());
        BeanUtils.copyNonNullProperties(news, excitedNews);
        excitedNews.setCategory(excitedCategory);
        return newsRepository.save(excitedNews);
    }

    @Override
    public void deleteById(Long id, String userName) {
        News excitedNews = findById(id);
        User user = userService.findByUsername(userName);
        if (user.hasOnlyRoleUser() && !Objects.equals(excitedNews.getUser().getId(), user.getId())){
            throw new VerificationException(MessageFormat.format("Новость {0} вам не принадлежит. Вы можете удалять только свои новости.",
                    excitedNews.getDescription()));
        }

        newsRepository.deleteById(id);
    }

    @Override
    public List<News> filterBy(NewsFilterRequest request, Integer page, Integer size) {
        return newsRepository.findAll(NewsSpecification.findWithFilter(request),
                PageRequest.of(page,size)
        ).getContent();
    }

}
