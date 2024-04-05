package com.example.SpringRestAPI.web.dto.newscategories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsCategoryListResponse {

    private List<NewsCategoryResponse> categories = new ArrayList<>();
}
