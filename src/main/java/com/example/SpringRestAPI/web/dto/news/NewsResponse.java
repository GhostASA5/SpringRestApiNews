package com.example.SpringRestAPI.web.dto.news;

import lombok.Data;

@Data
public class NewsResponse {

    private Long id;

    private String description;

    private Integer commentsCount;
}
