package ru.bogdsvn.location.store.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class ProfileEntity {
    @Id
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    private LocationEntity location;
}
