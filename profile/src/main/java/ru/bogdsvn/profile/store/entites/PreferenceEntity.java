package ru.bogdsvn.profile.store.entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;
import ru.bogdsvn.profile.utils.Gender;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "preferences")
public class PreferenceEntity implements Persistable<Long> {
    @Id
    private Long id;

    @Transient
    @Builder.Default
    private boolean isNew = true;

    private Integer ageLowerBound;

    private Integer ageUpperBound;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Override
    public boolean isNew() {
        return isNew;
    }
}
