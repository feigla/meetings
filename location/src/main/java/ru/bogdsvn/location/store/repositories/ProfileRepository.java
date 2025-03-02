package ru.bogdsvn.location.store.repositories;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bogdsvn.location.store.entities.ProfileEntity;

import java.util.List;


@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    // 0.3 ~ 33,3km
    @Query(value = """
    SELECT *
    FROM locations l
    WHERE ST_DWithin(l.point,:point,1)
    AND l.id != :id
    ORDER BY round(cast(st_distancesphere(l.point, :point) as numeric), 2)
    """, nativeQuery = true)
    List<ProfileEntity> findNearbyUsers(@Param("point") Point point, @Param("id") Long id);
}
