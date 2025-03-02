package ru.bogdsvn.location.services.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.bogdsvn.grcp.proximity.Proximity;
import ru.bogdsvn.grcp.proximity.ProximityServiceGrpc;
import ru.bogdsvn.location.dtos.UserDto;
import ru.bogdsvn.location.services.LocationService;
import ru.bogdsvn.location.store.entities.ProfileEntity;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@GrpcService
public class GrpcProximityServerService extends ProximityServiceGrpc.ProximityServiceImplBase {
    private final LocationService locationService;

    @Override
    public void getNearbyProfiles(Proximity.ProfileRequest request, StreamObserver<Proximity.ProfileList> responseObserver) {
        List<UserDto> users = locationService.getNearbyUsers(request.getUserId());

        Proximity.ProfileList.Builder builder = Proximity.ProfileList.newBuilder();

        for (UserDto user : users) {
            builder.addProfile(Proximity.ProfileList.Profile
                    .newBuilder()
                    .setUserId(user.getId())
                    .setDist(user.getDist())
                    .build());
        }

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
