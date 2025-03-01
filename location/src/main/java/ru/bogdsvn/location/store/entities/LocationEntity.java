package ru.bogdsvn.location.store.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.geo.Point;

import java.util.ArrayList;
import java.util.List;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "profiles")
@Entity
@Table(name = "locations")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "geometry(Point,4326)", nullable = false)
    private Point point;

    @Builder.Default
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProfileEntity> profiles = new ArrayList<>();
}
