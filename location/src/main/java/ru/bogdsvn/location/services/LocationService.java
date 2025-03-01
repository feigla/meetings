package ru.bogdsvn.location.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.location.dtos.LocationDto;
import ru.bogdsvn.location.dtos.UserDto;
import ru.bogdsvn.location.store.entities.LocationEntity;
import ru.bogdsvn.location.store.repositories.LocationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class LocationService {
    private final LocationRepository locationRepository;

    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    @Transactional
    public void saveLocation(LocationDto locationDto, long userId) {
        Point p = geometryFactory.createPoint(new Coordinate(locationDto.getLongitude(), locationDto.getLatitude()));

        LocationEntity location = locationRepository.findById(userId).orElse(null);

        if (location == null) {
            locationRepository.save(LocationEntity.builder()
                    .id(userId)
                    .point(p)
                    .build());
        } else {
            location.setPoint(p);
        }
    }

    public List<UserDto> getNearbyUsers(long userId) {
        LocationEntity location = locationRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return locationRepository.findNearbyUsers(location.getPoint(), userId)
                .stream()
                .map(x -> UserDto.builder().id(x.getId()).build())
                .collect(Collectors.toList());
    }
}
