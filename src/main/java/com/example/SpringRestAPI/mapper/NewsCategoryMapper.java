package com.example.SpringRestAPI.mapper;

import com.example.SpringRestAPI.domain.NewsCategory;
import com.example.SpringRestAPI.web.dto.newscategories.NewsCategoryListResponse;
import com.example.SpringRestAPI.web.dto.newscategories.NewsCategoryRequest;
import com.example.SpringRestAPI.web.dto.newscategories.NewsCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsCategoryMapper {

    NewsCategory categoryRequestToCategory(NewsCategoryRequest request);

    @Mapping(source = "categoryId", target = "id")
    NewsCategory categoryRequestToCategory(Long categoryId, NewsCategoryRequest request);

    NewsCategoryResponse categoryToResponse(NewsCategory category);

    default NewsCategoryListResponse categoryListToResponseList(List<NewsCategory> categories) {
        NewsCategoryListResponse response = new NewsCategoryListResponse();
        response.setCategories(categories.stream().map(this::categoryToResponse).collect(Collectors.toList()));
        return response;
    }

}
