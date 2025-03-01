package ru.bogdsvn.location.services.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.bogdsvn.grcp.proximity.Proximity;
import ru.bogdsvn.grcp.proximity.ProximityServiceGrpc;
import ru.bogdsvn.location.services.LocationService;
import ru.bogdsvn.location.store.entities.ProfileEntity;
import ru.bogdsvn.location.store.repositories.ProfileRepository;

@RequiredArgsConstructor
@GrpcService
public class GrpcProximityServerService extends ProximityServiceGrpc.ProximityServiceImplBase {
    private final ProfileRepository profileRepository;
    private final LocationService locationService;

    @Override
    public void getNearbyProfiles(Proximity.ProfileList.Profile request, StreamObserver<Proximity.ProfileList> responseObserver) {
        ProfileEntity entity = profileRepository.findById(request.getUserId()).get();
    }
}
