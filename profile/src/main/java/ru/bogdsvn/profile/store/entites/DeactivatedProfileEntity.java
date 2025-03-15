package ru.bogdsvn.profile.store.entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;
import ru.bogdsvn.kafka_library.utils.Status;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "deactivated_profiles")
public class DeactivatedProfileEntity implements Persistable<Long> {
    @Id
    private Long id;

    @Transient
    @Builder.Default
    private boolean isNew = true;

    @Enumerated
    @Builder.Default
    private Status status = Status.DEACTIVATE_PROCESSED;

    @Override
    public boolean isNew() {
        return isNew;
    }
}
