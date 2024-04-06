package com.example.SpringRestAPI.web.dto.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsRequest {

    @NotBlank(message = "Описание новости не должно быть пустым.")
    private String description;

    @NotNull(message = "Id пользователя не должно быть пустым.")
    private Long userId;

    @NotNull(message = "Id категории новостей не должно быть пустым.")
    private Long categoryId;

}
