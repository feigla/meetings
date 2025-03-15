package ru.bogdsvn.recommendation.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.recommendation.dtos.BioDto;
import ru.bogdsvn.recommendation.dtos.PreferenceDto;
import ru.bogdsvn.recommendation.dtos.ProfileDto;
import ru.bogdsvn.recommendation.dtos.ResultDto;
import ru.bogdsvn.recommendation.factories.ResultFactory;
import ru.bogdsvn.recommendation.factories.ViewedProfileFactory;
import ru.bogdsvn.recommendation.services.grpc.GrpcProfileClientService;
import ru.bogdsvn.recommendation.services.grpc.GrpcProximityClientService;
import ru.bogdsvn.recommendation.store.entities.ViewedProfileEntity;
import ru.bogdsvn.recommendation.store.repositories.ViewedProfileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class RecommendationService {
    private final GrpcProximityClientService grpcProximityClientService;
    private final GrpcProfileClientService grpcProfileClientService;
    private final ViewedProfileRepository viewedProfileRepository;

    private final ViewedProfileFactory viewedProfileFactory;
    private final ResultFactory resultFactory;

    @Transactional
    public List<ResultDto> getRecommendation(long id) {
        List<ViewedProfileEntity> profiles = viewedProfileRepository.getLimitedViewedProfiles(id, Pageable.ofSize(5));
        List<ResultDto> recommendations;
        if (profiles.isEmpty()) {
            List<ResultDto> filteredProfiles = fetchUsers(id);
            profiles = filteredProfiles.stream()
                    .limit(50L)
                    .skip(5)
                    .map(x -> viewedProfileFactory.makeViewedProfileEntity(x, id))
                    .collect(Collectors.toList());
            if (!profiles.isEmpty()) {
                viewedProfileRepository.saveAll(profiles);
            }
            recommendations = filteredProfiles.stream()
                    .limit(5)
                    .collect(Collectors.toList());
        } else {
            recommendations = profiles.stream()
                    .map(resultFactory::makeResultDto)
                    .collect(Collectors.toList());
            viewedProfileRepository.deleteAllInBatch(profiles);
        }
        return recommendations;
    }

    public List<ResultDto> fetchUsers(long id) {
        PreferenceDto userPreference = grpcProfileClientService.getPreference(id);
        List<ProfileDto> nearbyProfiles = grpcProximityClientService.getNearbyProfiles(id);

        return filterProfiles(nearbyProfiles, userPreference);
    }

    private List<ResultDto> filterProfiles(List<ProfileDto> nearbyProfiles, PreferenceDto userPreference) {
        return nearbyProfiles
                .stream()
                .map(p -> {
                    BioDto bio = grpcProfileClientService.getBio(p.getId());
                    if (bio == null) {
                        return null;
                    }
                    return resultFactory.makeResultDto(bio, p);
                })
                .filter(rd -> rd != null &&
                        rd.getGender().equals(userPreference.getGender()) &&
                        (userPreference.getAgeLowerBound() <= rd.getAge() && rd.getAge() <= userPreference.getAgeUpperBound()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteViewedProfilesById(long id) {
        viewedProfileRepository.deleteViewedProfilesById(id);
    }
}
