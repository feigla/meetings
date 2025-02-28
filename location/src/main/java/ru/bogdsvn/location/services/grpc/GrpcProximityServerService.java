package ru.bogdsvn.location.services.grpc;

import com.google.common.geometry.S2CellId;
import com.google.common.geometry.S2LatLng;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.bogdsvn.grcp.proximity.Proximity;
import ru.bogdsvn.grcp.proximity.ProximityServiceGrpc;
import ru.bogdsvn.location.services.LocationService;
import ru.bogdsvn.location.store.entities.ProfileEntity;
import ru.bogdsvn.location.store.repositories.ProfileRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@GrpcService
public class GrpcProximityServerService extends ProximityServiceGrpc.ProximityServiceImplBase {
    private final ProfileRepository profileRepository;
    private final LocationService locationService;

    @Override
    public void getNearbyProfiles(Proximity.ProfileList.Profile request, StreamObserver<Proximity.ProfileList> responseObserver) {
        ProfileEntity entity = profileRepository.findById(request.getUserId()).get();

        S2CellId cellId = new S2CellId(entity.getLocation().getId());
        S2LatLng latLng = cellId.toLatLng();
        List<ProfileEntity> profiles =  locationService.searchNearbyProfiles(latLng);

        Proximity.ProfileList profileList = Proximity.ProfileList
                .newBuilder()
                .addAllProfile(
                        profiles
                                .stream()
                                .map((ProfileEntity e) ->
                                        Proximity.ProfileList.Profile
                                                .newBuilder()
                                                .setUserId(e.getUserId())
                                                .build()
                                ).filter((Proximity.ProfileList.Profile p) ->
                                        (long)p.getUserId() != (long)request.getUserId()
                                ).collect(Collectors.toList())
                )
                .build();

        responseObserver.onNext(profileList);
        responseObserver.onCompleted();
    }
}
