package ru.bogdsvn.recommendation.store.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;
import ru.bogdsvn.recommendation.store.ViewedProfileEmbeddedId;

import java.io.Serializable;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "viewed_profiles")
public class ViewedProfileEntity implements Persistable<ViewedProfileEmbeddedId> {
    @EmbeddedId
    private ViewedProfileEmbeddedId id;

    @Builder.Default
    @Transient
    private boolean isNew = true;

    private String name;

    private Integer age;

    private String gender;

    private String description;

    private Double dist; // in meters

    @Override
    public boolean isNew() {
        return isNew;
    }
}
