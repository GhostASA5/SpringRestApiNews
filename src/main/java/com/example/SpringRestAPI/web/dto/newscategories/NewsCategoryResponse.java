package com.example.SpringRestAPI.web.dto.newscategories;

import com.example.SpringRestAPI.web.dto.news.NewsListResponse;
import com.example.SpringRestAPI.web.dto.news.NewsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsCategoryResponse {

    private Long id;

    private String category;

    private List<NewsResponse> news = new ArrayList<>();


}
