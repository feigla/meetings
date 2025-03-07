package ru.bogdsvn.recommendation.store.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bogdsvn.recommendation.store.ViewedProfileEmbeddedId;
import ru.bogdsvn.recommendation.store.entities.ViewedProfileEntity;

import java.util.List;

@Repository
public interface ViewedProfileRepository extends JpaRepository<ViewedProfileEntity, ViewedProfileEmbeddedId> {
    @Query(value = """
        SELECT p FROM ViewedProfileEntity p
        WHERE p.id.profileId = :id
    """)
    List<ViewedProfileEntity> getLimitedViewedProfiles(@Param("id") Long id, Pageable page);
}
