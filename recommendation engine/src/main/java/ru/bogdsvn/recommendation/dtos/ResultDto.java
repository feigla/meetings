package ru.bogdsvn.recommendation.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

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
