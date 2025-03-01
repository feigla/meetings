package ru.bogdsvn.location.store.entities;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Persistable;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locations")
public class LocationEntity implements Persistable<Long> {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Builder.Default
    @Transient
    private boolean isNew = true;

    @Column(columnDefinition = "geometry(Point,4326)", nullable = false)
    private Point point;

    @Override
    public boolean isNew() {
        return isNew;
    }
}
