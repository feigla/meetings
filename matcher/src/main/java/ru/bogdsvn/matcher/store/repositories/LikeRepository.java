package ru.bogdsvn.matcher.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bogdsvn.matcher.store.LikeEmbeddedId;
import ru.bogdsvn.matcher.store.entities.LikeEntity;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, LikeEmbeddedId> {
}
