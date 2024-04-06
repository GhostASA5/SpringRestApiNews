package com.example.SpringRestAPI.mapper;

import com.example.SpringRestAPI.domain.News;
import com.example.SpringRestAPI.mapper.delegates.NewsMapperDelegate;
import com.example.SpringRestAPI.web.dto.news.NewsByIdResponse;
import com.example.SpringRestAPI.web.dto.news.NewsListResponse;
import com.example.SpringRestAPI.web.dto.news.NewsRequest;
import com.example.SpringRestAPI.web.dto.news.NewsResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsMapper {

    News newsRequestToNews(NewsRequest request);

    @Mapping(source = "newsId", target = "id")
    News newsRequestToNews(Long newsId, NewsRequest request);

    NewsByIdResponse newsByIdToResponse(News news);

    NewsResponse newsToResponse(News news);

    default NewsListResponse newsListToResponseList(List<News> news) {
        NewsListResponse response = new NewsListResponse();
        response.setNews(news.stream().map(this::newsToResponse).collect(Collectors.toList()));
        return response;
    }
}
