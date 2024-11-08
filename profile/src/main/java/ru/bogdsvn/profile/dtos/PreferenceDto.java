package ru.bogdsvn.profile.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreferenceDto {
    @JsonProperty("gender")
    private String gender;

    @JsonProperty("age_lower_bound")
    private Integer ageLowerBound;

    @JsonProperty("age_upper_bound")
    private Integer ageUpperBound;
}
