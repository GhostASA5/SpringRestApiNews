package com.example.SpringRestAPI.web.dto.news;

import lombok.Data;

@Data
public class NewsRequest {

    private String description;

    private Long userId;

    private Long categoryId;

}
