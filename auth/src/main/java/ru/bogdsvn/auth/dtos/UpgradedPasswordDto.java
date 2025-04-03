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
public class UpgradedPasswordDto {
    @Size(min = 5, max = 20, message = "Имя пользователя должно содержать от 5 до 20 символов")
    @JsonProperty("username")
    private String username;

    @Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов")
    @JsonProperty("old_password")
    private String oldPassword;

    @Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов")
    @JsonProperty("new_password")
    private String newPassword;
}
