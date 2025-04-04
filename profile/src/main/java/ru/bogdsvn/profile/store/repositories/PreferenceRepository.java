package ru.bogdsvn.profile.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bogdsvn.profile.store.entites.PreferenceEntity;


@Repository
public interface PreferenceRepository extends JpaRepository<PreferenceEntity, Long> {
}
