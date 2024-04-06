package com.example.SpringRestAPI.web.dto.user;

import com.example.SpringRestAPI.web.dto.comment.CommentResponse;
import com.example.SpringRestAPI.web.dto.news.NewsByIdResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String name;

    private List<NewsByIdResponse> news = new ArrayList<>();

    private List<CommentResponse> comments = new ArrayList<>();
}
