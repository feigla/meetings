package ru.bogdsvn.location.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationDto {
    @JsonProperty("latitude")
    @Min(value = 0, message = "Широта имеет значения от 0 градусов до 90 градусов")
    @Max(value = 90, message = "Широта имеет значения от 0 градусов до 90 градусов")
    @NotNull
    private Double latitude;

    @JsonProperty("longitude")
    @Min(value = 0, message = "Долгота имеет значения от 0 градусов до 180 градусов")
    @Max(value = 180, message = "Долгота имеет значения от 0 градусов до 180 градусов")
    @NotNull
    private Double longitude;
}
