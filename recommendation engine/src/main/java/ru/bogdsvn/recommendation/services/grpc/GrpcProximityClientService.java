package ru.bogdsvn.recommendation.services.grpc;

import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.bogdsvn.grcp.proximity.Proximity;
import ru.bogdsvn.grcp.proximity.ProximityServiceGrpc;
import ru.bogdsvn.recommendation.dtos.ProfileDto;

import java.util.List;
import java.util.stream.Collectors;


@Log4j2
@RequiredArgsConstructor
@Service
public class GrpcProximityClientService extends ProximityServiceGrpc.ProximityServiceImplBase {
    @GrpcClient("location-service")
    private ProximityServiceGrpc.ProximityServiceBlockingStub blockingStub;

    public List<ProfileDto> getNearbyProfiles(final long id) {
        try {
            final Proximity.ProfileList response = blockingStub.getNearbyProfiles(Proximity.ProfileRequest.newBuilder().setUserId(id).build());
            return response
                    .getProfileList()
                    .stream()
                    .map((Proximity.ProfileList.Profile p) ->
                            ProfileDto
                                    .builder()
                                    .id(p.getUserId())
                                    .dist(p.getDist())
                                    .build()
                    ).collect(Collectors.toList());
        } catch (StatusRuntimeException e) {
            log.error(e);
        }
        throw new RuntimeException("Not found");
    }
}
