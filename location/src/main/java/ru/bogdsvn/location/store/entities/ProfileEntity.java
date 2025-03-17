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
@Table(name = "profiles")
public class ProfileEntity implements Persistable<Long> {
    @Id
    @Column(name = "profile_id")
    private Long id;

    @Builder.Default
    @Transient
    private boolean isNew = true;

    @Column(columnDefinition = "geometry(Point,4326)", nullable = false)
    private Point point;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @Override
    public boolean isNew() {
        return isNew;
    }
}
