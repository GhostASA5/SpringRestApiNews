package com.example.SpringRestAPI.repositories;

import com.example.SpringRestAPI.domain.News;
import com.example.SpringRestAPI.web.dto.news.NewsFilterRequest;
import org.springframework.data.jpa.domain.Specification;

public interface NewsSpecification {

    static Specification<News> findWithFilter(NewsFilterRequest filter){
        return Specification.where(byNewsCategory(filter.getCategoryId()))
                .and(byUser(filter.getUserId()));
    }

    static Specification<News> byNewsCategory(Long categoryId){
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        };
    }

    static Specification<News> byUser(Long userId){
        return (root, query, criteriaBuilder) -> {
            if (userId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("user").get("id"), userId);
        };
    }
}
