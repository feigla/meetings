package ru.bogdsvn.profile.store.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "profiles")
public class ProfileEntity {
    @Id
    private Long id;

    @Builder.Default
    private Instant createdAt = Instant.now();
}