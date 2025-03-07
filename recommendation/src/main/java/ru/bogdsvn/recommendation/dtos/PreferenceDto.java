package ru.bogdsvn.recommendation.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreferenceDto {
    private String gender;
    private Integer ageLowerBound;
    private Integer ageUpperBound;
}
