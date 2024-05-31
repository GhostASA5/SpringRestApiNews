package com.example.SpringRestAPI.web.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

    @NotBlank(message = "Имя пользовател не должно быть пустым.")
    private String username;

    @NotBlank(message = "Пароль не должен быть пустым.")
    private String password;

}
