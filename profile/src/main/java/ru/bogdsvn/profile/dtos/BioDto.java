package ru.bogdsvn.profile.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bogdsvn.kafka_library.utils.Status;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BioDto {
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "status", access = JsonProperty.Access.READ_ONLY)
    private Status status;

    @JsonProperty("name")
    @Size(min = 1, max = 45, message = "Имя пользователя должно иметь длину до 45 символов")
    @NotNull
    private String name;

    @JsonProperty("age")
    @Min(value = 18, message = "Возраст должен быть как минимум 18 лет")
    @Max(value = 80, message = "Возвраст не должен превышать 80 лет")
    @NotNull
    private Integer age;

    @JsonProperty("gender")
    @Pattern(regexp = "MALE|FEMALE", message = "Пол может быть MALE или FEMALE")
    @NotNull
    private String gender;

    @JsonProperty("description")
    @Size(max = 255, message = "Описание не должно превышать 255 символов")
    @NotNull
    private String description;
}
