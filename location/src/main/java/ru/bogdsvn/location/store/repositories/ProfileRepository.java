package ru.bogdsvn.location.store.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bogdsvn.location.store.entities.ProfileEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    @Query("""
        SELECT p
        FROM ProfileEntity p
        WHERE p.location.id = :cell_id
    """)
    List<ProfileEntity> findProfilesById(@Param("cell_id") Long cellId, Pageable pageable);

    Optional<ProfileEntity> findById(Long id);
}
