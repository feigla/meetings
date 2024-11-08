package ru.bogdsvn.profile.store.entites;

import jakarta.persistence.*;
import lombok.*;
import ru.bogdsvn.profile.utils.Gender;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bios")
public class BioEntity {
    @Id
    private String id;

    private String name;

    private Integer age;

    private String description;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private ProfileEntity profile;
}
