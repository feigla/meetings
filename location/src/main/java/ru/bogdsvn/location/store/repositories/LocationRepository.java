package ru.bogdsvn.location.store.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bogdsvn.location.store.entities.LocationEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    Optional<LocationEntity> findById(@Param("id") Long id);

    @Query("""
        SELECT location
        FROM LocationEntity location
        WHERE :begin_cell_id <= location.id AND location.id <= :end_cell_id
    """)
    List<LocationEntity> findInRange(@Param("begin_cell_id") long beginCellId, @Param("end_cell_id") long endCellId, Pageable pageable);
}
