package ru.bogdsvn.location.services;

import com.google.common.geometry.*;
import org.springframework.stereotype.Service;

@Service
public class S2Service {
    private final static int EARTH_RADIUS = 6371;

    public long getCellId(double latitude, double longitude, int level) {
        S2LatLng latlng = S2LatLng.fromDegrees(latitude, longitude);
        S2CellId cellID = S2CellId.fromLatLng(latlng);
        S2CellId levelsCellId = cellID.parent(level);
        return levelsCellId.id();
    }

    public S2CellUnion getUnion(S2LatLng latLng, int level, int RADIUS) {
        S2Point centrePoint = latLng.toPoint();

        double centreAngle = (double) RADIUS / EARTH_RADIUS;

        S2Cap cap = S2Cap.fromAxisAngle(centrePoint, S1Angle.radians(centreAngle));

        S2RegionCoverer rc = S2RegionCoverer
                .builder()
                .setMinLevel(level)
                .setMaxLevel(level)
                .build();
        return rc.getCovering(cap);
    }
}
