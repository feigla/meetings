package ru.bogdsvn.profile.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import ru.bogdsvn.profile.annotations.ValidAgeRange;

@ValidAgeRange
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreferenceDto {
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty("gender")
    @Pattern(regexp = "MALE|FEMALE", message = "Пол может быть MALE или FEMALE")
    @NotNull
    private String gender;

    @JsonProperty("age_lower_bound")
    @Min(value = 18, message = "Возраст должен быть как минимум 18 лет")
    @Max(value = 80, message = "Возвраст не должен превышать 80 лет")
    @NotNull
    private Integer ageLowerBound;

    @JsonProperty("age_upper_bound")
    @Min(value = 18, message = "Возраст должен быть как минимум 18 лет")
    @Max(value = 80, message = "Возвраст не должен превышать 80 лет")
    @NotNull
    private Integer ageUpperBound;
}
