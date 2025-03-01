package ru.bogdsvn.location.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bogdsvn.location.store.entities.LocationEntity;


@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
}
