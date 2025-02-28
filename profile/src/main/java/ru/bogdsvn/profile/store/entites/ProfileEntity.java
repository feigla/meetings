package ru.bogdsvn.profile.store.entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "profiles")
public class ProfileEntity implements Persistable<Long> {
    @Id
    private Long id;

    @Builder.Default
    @Transient
    private boolean isNew = true;

    @Builder.Default
    private Instant createdAt = Instant.now();

    @Override
    public boolean isNew() {
        return isNew;
    }
}