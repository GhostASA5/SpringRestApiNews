package com.example.SpringRestAPI.web.dto.newscategories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsCategoryRequest {

    @NotBlank(message = "Имя пользовател не должно быть пустым.")
    private String category;

}
