package ru.bogdsvn.matcher.store;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class LikeEmbeddedId {
    @Column(name = "profile_id")
    private Long id;

    @Column(name = "liked_profile_id")
    private Long likedId;
}
