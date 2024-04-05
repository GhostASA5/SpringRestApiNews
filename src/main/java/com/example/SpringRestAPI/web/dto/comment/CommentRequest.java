package com.example.SpringRestAPI.web.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentRequest {

    private String comment;

    private Long userId;

    private Long newsId;

}
