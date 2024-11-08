package ru.bogdsvn.location.store.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locations")
public class LocationEntity {
    @Id
    private Long id;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private List<ProfileEntity> profiles = new ArrayList<>();

    public void addProfile(ProfileEntity profile) {
        profiles.add(profile);
    }

    public void removeProfile(ProfileEntity profile) {
        profiles.remove(profile);
    }
}
