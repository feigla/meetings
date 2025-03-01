package ru.bogdsvn.location.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.location.dtos.LocationDto;
import ru.bogdsvn.location.store.repositories.LocationRepository;
import ru.bogdsvn.location.store.repositories.ProfileRepository;

@Log4j2
@RequiredArgsConstructor
@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    public void saveLocation(LocationDto locationDto, long userId) {
    }
}
