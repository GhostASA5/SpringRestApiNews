package com.example.SpringRestAPI.repositories;

import com.example.SpringRestAPI.domain.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Long> {
}
