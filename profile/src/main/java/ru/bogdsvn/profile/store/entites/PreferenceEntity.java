package ru.bogdsvn.profile.store.entites;

import jakarta.persistence.*;
import lombok.*;
import ru.bogdsvn.profile.utils.Gender;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "preferences")
public class PreferenceEntity {
    @Id
    private String id;

    private Integer ageLowerBound;

    private Integer ageUpperBound;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private ProfileEntity profile;
}
