package ru.bogdsvn.profile.store.entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;
import ru.bogdsvn.profile.utils.Gender;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bios")
public class BioEntity implements Persistable<Long> {
    @Id
    private Long id;

    @Transient
    @Builder.Default
    private boolean isNew = true;

    private String name;

    private Integer age;

    private String description;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Override
    public boolean isNew() {
        return isNew;
    }
}
