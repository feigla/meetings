package ru.bogdsvn.profile.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bogdsvn.profile.store.entites.DeactivatedProfileEntity;

@Repository
public interface DeactivatedProfileRepository extends JpaRepository<DeactivatedProfileEntity, Long> {
    @Modifying
    @Query("""
    DELETE FROM DeactivatedProfileEntity p
    WHERE p.id = :id
    """)
    void deleteById(@Param("id") Long id);
}
