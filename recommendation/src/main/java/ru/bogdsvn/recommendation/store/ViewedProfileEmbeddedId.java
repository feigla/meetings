package ru.bogdsvn.recommendation.store;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class ViewedProfileEmbeddedId {
    @Column(name = "profile_id")
    private Long profileId;
    @Column(name = "viewed_profile_id")
    private Long viewedProfileId;
}
