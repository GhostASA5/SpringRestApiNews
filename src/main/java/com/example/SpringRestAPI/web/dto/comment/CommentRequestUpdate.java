package com.example.SpringRestAPI.web.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestUpdate {

    @NotBlank(message = "Комментарий не должен быть пустым.")
    private String comment;
}
