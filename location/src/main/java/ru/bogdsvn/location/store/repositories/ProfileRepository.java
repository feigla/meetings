package ru.bogdsvn.location.store.repositories;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bogdsvn.location.projections.ProfileProjection;
import ru.bogdsvn.location.store.entities.ProfileEntity;

import java.util.List;


@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    // 0.4 ~ 44,4km
    // dist - meters
    @Query(value = """
    SELECT l.profile_id, round(cast(st_distancesphere(l.point, :point) as numeric), 2) as dist
    FROM profiles l
    WHERE ST_DWithin(l.point,:point,0.4)
    AND l.profile_id != :id
    AND l.is_active = true
    ORDER BY dist ASC
    LIMIT 200
    """, nativeQuery = true)
    List<ProfileProjection> findNearbyUsers(@Param("point") Point point, @Param("id") Long id);
}
