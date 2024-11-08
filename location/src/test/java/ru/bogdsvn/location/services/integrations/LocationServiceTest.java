package ru.bogdsvn.location.services.integrations;

import com.google.common.geometry.S2LatLng;
import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.location.services.LocationService;
import ru.bogdsvn.location.services.S2Service;
import ru.bogdsvn.location.store.entities.LocationEntity;
import ru.bogdsvn.location.store.entities.ProfileEntity;
import ru.bogdsvn.location.store.repositories.LocationRepository;
import ru.bogdsvn.location.store.repositories.ProfileRepository;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LocationServiceTest {
    @Autowired
    private LocationService locationService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ru.bogdsvn.location.services.S2Service S2Service;

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ru.bogdsvn.location.services.S2Service s2Service;

    @Transactional
    @BeforeEach
    public void setUpLocation() {
        LocationEntity location = locationRepository.saveAndFlush(
                LocationEntity
                        .builder()
                        .id(s2Service.getCellId(0, 180, 10))
                        .build()
        );
        List<ProfileEntity> profiles = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            profiles.add(
                    ProfileEntity.builder()
                            .userId(String.valueOf(i))
                            .location(location)
                            .build()
            );
        }
        profileRepository.saveAll(profiles);
    }

    @Test
    public void givenLatitudeAndLongitudeAndLevel_whenSearchNearly_thenReturnCorrectNearlyProfiles () {
        long s = System.currentTimeMillis();
        Assertions.assertThat(locationService.searchNearbyProfiles(S2LatLng.fromDegrees(0, 180))).isNotEmpty();
        long en  = System.currentTimeMillis();
        System.out.println((en - s));
    }

    @Transactional
    @AfterEach
    public void ClearUpLocation() {
        locationRepository.deleteById(S2Service.getCellId(0, 180, 10));
    }
}
