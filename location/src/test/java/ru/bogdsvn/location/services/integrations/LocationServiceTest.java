package ru.bogdsvn.location.services.integrations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.bogdsvn.location.dtos.LocationDto;
import ru.bogdsvn.location.dtos.UserDto;
import ru.bogdsvn.location.services.LocationService;
import ru.bogdsvn.location.store.repositories.ProfileRepository;

import java.util.List;


@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class LocationServiceTest {
    @Autowired
    private LocationService locationService;

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void givenLocationDtoAndUserId_shouldCorrectSaveLocation() {
        locationService.saveLocation(
                LocationDto.builder()
                        .longitude(60.)
                        .latitude(60.)
                        .build(),
                2L
        );
        Assertions.assertFalse(profileRepository.findById(2L).isEmpty());
    }

    @Test
    public void givenUsers_shouldReturnCorrectNearbyUsers() {
        locationService.saveLocation(
                LocationDto.builder()
                        .longitude(60.)
                        .latitude(60.)
                        .build(),
                1L
        );
        locationService.saveLocation(
                LocationDto.builder()
                        .longitude(60.2)
                        .latitude(60.1)
                        .build(),
                2L
        );
        locationService.saveLocation(
                LocationDto.builder()
                        .longitude(60.3)
                        .latitude(60.)
                        .build(),
                3L
        );
        List<UserDto> list = locationService.getNearbyUsers(1L);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(2L, (long) list.get(0).getId());
        Assertions.assertEquals(3L, (long) list.get(1).getId());
    }
}
