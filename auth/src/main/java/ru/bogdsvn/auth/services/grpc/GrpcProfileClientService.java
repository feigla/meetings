package ru.bogdsvn.auth.services.grpc;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.bogdsvn.grcp.profile.ProfileOuterClass;
import ru.bogdsvn.grcp.profile.ProfileServiceGrpc;


@Slf4j
@Service
public class GrpcProfileClientService extends ProfileServiceGrpc.ProfileServiceImplBase {
    @GrpcClient("profile-service")
    private ProfileServiceGrpc.ProfileServiceBlockingStub blockingStub;

    public void saveProfile(long userId) {
        ProfileOuterClass.Response response = blockingStub.saveProfile(ProfileOuterClass.Profile.newBuilder().setUserId(userId).build());
        if (!response.getOk()) {
            throw new RuntimeException("Exception with save profile");
        }
    }
}
