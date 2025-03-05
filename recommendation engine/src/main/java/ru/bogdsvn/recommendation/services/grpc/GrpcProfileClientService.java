package ru.bogdsvn.recommendation.services.grpc;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.ProtoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.bogdsvn.grcp.profile.ProfileOuterClass;
import ru.bogdsvn.grcp.profile.ProfileServiceGrpc;
import ru.bogdsvn.recommendation.dtos.BioDto;
import ru.bogdsvn.recommendation.dtos.PreferenceDto;
import ru.bogdsvn.recommendation.errors.PreferenceNotFoundException;

@Log4j2
@RequiredArgsConstructor
@Service
public class GrpcProfileClientService extends ProfileServiceGrpc.ProfileServiceImplBase {
    @GrpcClient("profile-service")
    private ProfileServiceGrpc.ProfileServiceBlockingStub blockingStub;

    public PreferenceDto getPreference(final long id) {
        try {
            final ProfileOuterClass.Preference preference = blockingStub.getPreference(ProfileOuterClass.Profile.newBuilder().setUserId(id).build());
            return PreferenceDto.builder()
                    .gender(preference.getGender())
                    .ageLowerBound(preference.getAgeLowerBound())
                    .ageUpperBound(preference.getAgeUpperBound())
                    .build();
        } catch (StatusRuntimeException e) {
            ProfileOuterClass.ErrorResponse errorResponse = Status.trailersFromThrowable(e)
                    .get(ProtoUtils.keyForProto(ProfileOuterClass.ErrorResponse.getDefaultInstance()));
            throw new PreferenceNotFoundException(errorResponse.getMessage());
        }
    }

    /**
     *
     * @param id
     * @return null if bioDto is none, otherwise BioDto
     */
    public BioDto getBio(final long id) {
        try {
            final ProfileOuterClass.Bio bio = blockingStub.getBio(ProfileOuterClass.Profile.newBuilder().setUserId(id).build());
            return BioDto
                    .builder()
                    .name(bio.getName())
                    .gender(bio.getGender())
                    .description(bio.getDescription())
                    .age(bio.getAge())
                    .build();
        } catch (StatusRuntimeException e) {
            return null;
        }
    }
}
