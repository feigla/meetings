package ru.bogdsvn.auth.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtAuthResponseDto {
    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    @JsonProperty(value = "access_token")
    private String accessToken;
}
