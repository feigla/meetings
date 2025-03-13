package ru.bogdsvn.profile.store.entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class ProfileEntity implements Persistable<Long> {
    @Id
    private Long id;

    @Transient
    @Builder.Default
    private boolean isNew = true;

    @Column(name = "location_updated")
    @Builder.Default
    private Boolean locationUpdated = false;

    @Column(name = "recommendation_updated")
    @Builder.Default
    private Boolean recommendationUpdated = false;

    @Override
    public boolean isNew() {
        return isNew;
    }
}
