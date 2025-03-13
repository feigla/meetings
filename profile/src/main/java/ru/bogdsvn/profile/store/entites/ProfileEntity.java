package ru.bogdsvn.profile.store.entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class ProfileEntity implements Persistable<Long> {
    @Id
    private Long id;

    @Transient
    @Builder.Default
    private boolean isNew = true;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @Override
    public boolean isNew() {
        return isNew;
    }
}
