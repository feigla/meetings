package ru.bogdsvn.matcher.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bogdsvn.matcher.store.LikeEmbeddedId;
import ru.bogdsvn.matcher.store.entities.LikeEntity;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, LikeEmbeddedId> {
    @Modifying
    @Query("""
    DELETE FROM LikeEntity e
    WHERE e.id = :id
    """)
    void deleteById(@Param("id") LikeEmbeddedId id);
}
