package ru.bogdsvn.matcher.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LikeDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("liked_id")
    private Long likedId;
}
