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
import ru.bogdsvn.location.errors.BadRequestException;
import ru.bogdsvn.location.errors.NotFoundException;
import ru.bogdsvn.location.store.entities.ProfileEntity;
import ru.bogdsvn.location.store.repositories.ProfileRepository;


import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class LocationService {
    private final ProfileRepository profileRepository;

    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    @Transactional
    public void saveLocation(LocationDto locationDto, long userId) {
        profileRepository
                .findById(userId)
                .ifPresent((profileEntity) -> {
                    throw new BadRequestException("Местоположение уже указано");
                });

        Point p = geometryFactory.createPoint(
                new Coordinate(
                        locationDto.getLongitude(),
                        locationDto.getLatitude()
                )
        );

        profileRepository.save(ProfileEntity.builder()
                .id(userId)
                .point(p)
                .build());
    }

    @Transactional
    public void updateLocation(LocationDto locationDto, long userId) {
        ProfileEntity profile = profileRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("Местоположение не найдено"));

        Point p = geometryFactory.createPoint(
                new Coordinate(
                        locationDto.getLongitude(),
                        locationDto.getLatitude()
                )
        );

        profile.setPoint(p);
    }

    public List<UserDto> getNearbyUsers(long userId) {
        ProfileEntity location = profileRepository.findById(userId).orElse(null);
        if (location == null) {
            return null;
        }
        return profileRepository.findNearbyUsers(location.getPoint(), userId)
                .stream()
                .map(x -> UserDto.builder().id(x.getProfileId()).dist(x.getDist()).build())
                .collect(Collectors.toList());
    }

    @Transactional
    public void setActive(long userId) {
        ProfileEntity location = profileRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("Местоположение не указано"));

        location.setIsActive(!location.getIsActive());
    }
}
