package ru.bogdsvn.profile.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bogdsvn.profile.store.entites.BioEntity;


@Repository
public interface BioRepository extends JpaRepository<BioEntity, Long> {
}
