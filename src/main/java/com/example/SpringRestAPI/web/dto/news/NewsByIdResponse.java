package com.example.SpringRestAPI.web.dto.news;

import com.example.SpringRestAPI.web.dto.comment.CommentResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsByIdResponse {

    private Long id;

    private String description;

    private List<CommentResponse> comments = new ArrayList<>();
}
