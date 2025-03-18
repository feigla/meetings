package ru.bogdsvn.profile.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bogdsvn.kafka_library.utils.Status;
import ru.bogdsvn.profile.store.entites.BioEntity;

import java.util.Optional;


@Repository
public interface BioRepository extends JpaRepository<BioEntity, Long> {
    @Query("""
    SELECT p.status FROM BioEntity p
    WHERE p.id = :id
    """)
    Optional<Status> getStatus(@Param("id") Long id);
}
