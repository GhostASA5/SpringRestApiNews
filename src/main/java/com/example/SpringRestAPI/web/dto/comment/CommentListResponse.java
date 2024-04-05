package com.example.SpringRestAPI.web.dto.comment;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentListResponse {

    private List<CommentResponse> comments = new ArrayList<>();
}
