package ru.bogdsvn.recommendation.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BioDto {
    private String name;
    private Integer age;
    private String gender;
    private String description;
}
