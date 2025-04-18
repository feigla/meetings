package ru.bogdsvn.profile.services.grpc;

import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.bogdsvn.grcp.profile.ProfileOuterClass;
import ru.bogdsvn.grcp.profile.ProfileServiceGrpc;
import ru.bogdsvn.profile.store.entites.BioEntity;
import ru.bogdsvn.profile.store.entites.PreferenceEntity;
import ru.bogdsvn.profile.store.repositories.BioRepository;
import ru.bogdsvn.profile.store.repositories.PreferenceRepository;

@Log4j2
@RequiredArgsConstructor
@GrpcService
public class GrpcProfileServerService extends ProfileServiceGrpc.ProfileServiceImplBase {
    private final BioRepository bioRepository;
    private final PreferenceRepository preferenceRepository;

    @Override
    public void getPreference(ProfileOuterClass.Profile request, StreamObserver<ProfileOuterClass.Preference> responseObserver) {
        PreferenceEntity entity = preferenceRepository.findById(request.getUserId()).orElse(null);

        if (entity == null) {
            Metadata.Key<ProfileOuterClass.ErrorResponse> errorResponseKey = ProtoUtils
                    .keyForProto(ProfileOuterClass.ErrorResponse.getDefaultInstance());
            ProfileOuterClass.ErrorResponse error = ProfileOuterClass.ErrorResponse.newBuilder()
                    .setMessage("Заполните свои интересы")
                    .build();
            Metadata metadata = new Metadata();
            metadata.put(errorResponseKey, error);
            responseObserver.onError(Status.NOT_FOUND.withDescription("Не заполнены интересы").asRuntimeException(metadata));
            return;
        }

        ProfileOuterClass.Preference preference  = ProfileOuterClass.Preference
                .newBuilder()
                .setGender(entity.getGender().value)
                .setAgeLowerBound(entity.getAgeLowerBound())
                .setAgeUpperBound(entity.getAgeUpperBound())
                .build();

        responseObserver.onNext(preference);
        responseObserver.onCompleted();
    }

    @Override
    public void getBio(ProfileOuterClass.Profile request, StreamObserver<ProfileOuterClass.Bio> responseObserver) {
        BioEntity entity = bioRepository.findById(request.getUserId()).orElse(null);

        if (entity == null) {
            responseObserver.onError(Status.NOT_FOUND.withDescription("Не заполнен профиль").asRuntimeException());
            return;
        }

        ProfileOuterClass.Bio bio  = ProfileOuterClass.Bio
                .newBuilder()
                .setName(entity.getName())
                .setGender(entity.getGender().value)
                .setAge(entity.getAge())
                .setDescription(entity.getDescription())
                .build();

        responseObserver.onNext(bio);
        responseObserver.onCompleted();
    }
}
