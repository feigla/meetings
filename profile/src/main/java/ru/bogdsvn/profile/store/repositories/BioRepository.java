package ru.bogdsvn.profile.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bogdsvn.profile.store.entites.BioEntity;
import ru.bogdsvn.profile.store.entites.PreferenceEntity;

import java.util.Optional;

@Repository
public interface BioRepository extends JpaRepository<BioEntity, Long> {
    Optional<BioEntity> findById(Long id);
}
