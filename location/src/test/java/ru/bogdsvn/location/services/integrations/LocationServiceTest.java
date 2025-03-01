package ru.bogdsvn.location.services.integrations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.bogdsvn.location.dtos.LocationDto;
import ru.bogdsvn.location.services.LocationService;
import ru.bogdsvn.location.store.repositories.LocationRepository;


@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class LocationServiceTest {
    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationRepository locationRepository;

    @Test
    public void givenLocationDtoAndUserId_shouldCorrectSaveLocation() {
        locationService.saveLocation(
                LocationDto.builder()
                        .longitude(60.)
                        .latitude(60.)
                        .build(),
                2L
        );
        Assertions.assertFalse(locationRepository.findById(2L).isEmpty());
    }
}
