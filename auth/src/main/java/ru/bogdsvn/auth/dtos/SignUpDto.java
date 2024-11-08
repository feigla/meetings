package ru.bogdsvn.auth.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpDto {
    @Size(min = 5, max = 20, message = "Имя пользователя должно содержать от 5 до 20 символов")
    @JsonProperty("username")
    private String username;

    @Size(max = 255, message = "Длина пароля должна быть не более 255 символов")
    @JsonProperty("password")
    private String password;
}
