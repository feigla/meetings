package ru.bogdsvn.location.services.integrations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.location.services.LocationService;
import ru.bogdsvn.location.store.repositories.LocationRepository;
import ru.bogdsvn.location.store.repositories.ProfileRepository;


@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class LocationServiceTest {
    @Autowired
    private LocationService locationService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Transactional
    @BeforeEach
    public void setUpLocation() {
    }


    @Test
    public void givenLocationDtoAndCellId_whenSaveLocation_thenNotChangeLocation() {
    }

    @Test
    public void givenLocationDtoAndCellId_whenSaveLocation_thenCorrectChangeLocation() {
    }

    @Test
    public void givenLatitudeAndLongitudeAndLevel_whenSearchNearly_thenReturnCorrectNearlyProfiles () {
    }

    @Transactional
    @AfterEach
    public void ClearUpLocation() {
    }
}
