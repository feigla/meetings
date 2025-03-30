package ru.bogdsvn.matcher.store.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;
import org.springframework.data.domain.Persistable;
import ru.bogdsvn.matcher.store.LikeEmbeddedId;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "likes")
public class LikeEntity implements Persistable<LikeEmbeddedId> {
    @EmbeddedId
    private LikeEmbeddedId id;

    @Transient
    @Builder.Default
    private Boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }
}
