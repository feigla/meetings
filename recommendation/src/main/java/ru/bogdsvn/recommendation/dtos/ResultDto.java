package ru.bogdsvn.recommendation.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultDto {
    @JsonIgnore
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String description;
    private Double dist; // in meters
}
