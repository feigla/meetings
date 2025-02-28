package ru.bogdsvn.profile.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bogdsvn.profile.store.entites.PreferenceEntity;
import ru.bogdsvn.profile.store.entites.ProfileEntity;

import java.util.Optional;

@Repository
public interface PreferenceRepository extends JpaRepository<PreferenceEntity, Long> {
    Optional<PreferenceEntity> findById(Long id);
}
