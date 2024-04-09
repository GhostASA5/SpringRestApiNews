package com.example.SpringRestAPI.services.impl;

import com.example.SpringRestAPI.domain.NewsCategory;
import com.example.SpringRestAPI.exceptions.EntityNotFoundException;
import com.example.SpringRestAPI.repositories.NewsCategoryRepository;
import com.example.SpringRestAPI.services.NewsCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsCategoryImpl implements NewsCategoryService {

    private final NewsCategoryRepository newsCategoryRepository;

    @Override
    public List<NewsCategory> findAll(Integer page, Integer size) {
        return newsCategoryRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public NewsCategory findById(Long id) {
        return newsCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Категория новостей с id {0} не найдена", id)));
    }

    @Override
    public NewsCategory save(NewsCategory category) {
        return newsCategoryRepository.save(category);
    }

    @Override
    public NewsCategory update(NewsCategory category) {
        Optional<NewsCategory> excitedNewsCategory = newsCategoryRepository.findById(category.getId());
        if (excitedNewsCategory.isPresent()){
            return newsCategoryRepository.save(category);
        }
        throw new EntityNotFoundException(MessageFormat.format("Категория новостей с id {0} не найдена", category.getId()));
    }

    @Override
    public void deleteById(Long id) {
        newsCategoryRepository.deleteById(id);
    }

}
