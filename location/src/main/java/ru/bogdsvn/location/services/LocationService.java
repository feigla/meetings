package ru.bogdsvn.location.services;

import com.google.common.geometry.S2CellId;
import com.google.common.geometry.S2LatLng;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.location.dtos.LocationDto;
import ru.bogdsvn.location.store.entities.LocationEntity;
import ru.bogdsvn.location.store.entities.ProfileEntity;
import ru.bogdsvn.location.store.repositories.LocationRepository;
import ru.bogdsvn.location.store.repositories.ProfileRepository;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class LocationService {
    private final static int LEVEL = 10;
    private final static int RADIUS = 50;
    private final static int PROFILE_LIMIT = 50;
    private final static int LOCATION_LIMIT = 10;

    private final LocationRepository locationRepository;
    private final ProfileRepository profileRepository;
    private final S2Service s2Service;

    @Transactional
    public void saveLocation(LocationDto locationDto, String userId) {
        long cellId = s2Service.getCellId(locationDto.getLatitude(), locationDto.getLongitude(), LEVEL);

        LocationEntity locationEntity = locationRepository.findById(cellId).orElse(null);

        if (locationEntity == null) {
            locationEntity = locationRepository.save(
                    LocationEntity
                            .builder()
                            .id(cellId)
                            .build()
            );
        } else if (locationEntity.getId() == cellId) {
            return;
        }

        ProfileEntity profileEntity = profileRepository.findById(userId).orElse(null);

        if (profileEntity == null) {
            profileEntity = ProfileEntity
                    .builder()
                    .userId(userId)
                    .build();
        } else {
            locationEntity.removeProfile(profileEntity);
        }

        locationEntity.addProfile(profileEntity);
    }

    @Transactional
    public List<ProfileEntity> searchNearbyProfiles(S2LatLng lanLng) {
        var cu = s2Service.getUnion(lanLng, LEVEL, RADIUS);
        List<ProfileEntity> profiles = new ArrayList<>();

        for (S2CellId cellId : cu.cellIds()) {
            if (cellId.level() < LEVEL) {
                S2CellId begin = cellId.childBegin(LEVEL);
                S2CellId end = cellId.childEnd(LEVEL);
//                if (begin.id() <= s2Service.getCellId(0, 180, LEVEL) && s2Service.getCellId(0, 180, LEVEL) <= end.id()) {
//                    log.info("!");
//                }
                if (!batchLoadLocations(begin.id(), end.id(), profiles)) {
                    break;
                }
            } else {
                LocationEntity location = locationRepository.findById(cellId.id()).orElse(null);
                if (location != null) {
                    if (!batchLoadProfiles(location.getId(), profiles));
                }
            }
        }

        return profiles.isEmpty() ? null : profiles;
    }

    /**
     *
     * @param cellId
     * @param profiles
     * @return true if the function can load more profiles; otherwise, returns false
     */
    @Transactional(propagation = Propagation.NESTED)
    public boolean batchLoadProfiles(long cellId, List<ProfileEntity> profiles) {
        for (int i = 0; ; i++) {
            List<ProfileEntity> processes = profileRepository.findProfilesById(cellId, Pageable.ofSize(PROFILE_LIMIT).withPage(i));
            profiles.addAll(processes.subList(0, Math.min(PROFILE_LIMIT, processes.size())));
            if (profiles.size() == PROFILE_LIMIT) {
                return false;
            } else if (processes.size() < PROFILE_LIMIT) {
                break;
            }
        }
        return true;
    }

    /**
     *
     * @param beginCellId
     * @param endCellId
     * @param profiles
     * @return true if the function can load more profiles; otherwise, returns false
     */
    @Transactional(propagation = Propagation.NESTED)
    public boolean batchLoadLocations(long beginCellId, long endCellId, List<ProfileEntity> profiles) {
        for (int i = 0; ; i++) {
            List<LocationEntity> locations = locationRepository
                    .findInRange(
                            beginCellId,
                            endCellId,
                            Pageable.ofSize(LOCATION_LIMIT).withPage(i)
                    );
            if (locations == null) {
                break;
            }
            if (!locations.isEmpty()) {
                for (LocationEntity location : locations) {
                    if (!batchLoadProfiles(location.getId(), profiles)) {
                        return false;
                    }
                }
            } else if (locations.size() < LOCATION_LIMIT) {
                break;
            }
        }
        return true;
    }
}
