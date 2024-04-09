package com.example.SpringRestAPI.web.dto.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsFilterRequest {

    private Long userId;

    private Long categoryId;

}
