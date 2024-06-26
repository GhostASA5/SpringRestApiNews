package com.example.SpringRestAPI.web.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestCreate {

    @NotBlank(message = "Комментарий не должен быть пустым.")
    private String comment;

    @NotNull(message = "Id пользователя не должно быть пустым.")
    private Long userId;

    @NotNull(message = "Id новости не должно быть пустым.")
    private Long newsId;

}
