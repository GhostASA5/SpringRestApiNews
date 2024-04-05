package com.example.SpringRestAPI.web.dto.comment;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {

    private Long id;

    private String comment;

}
