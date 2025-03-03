package ru.bogdsvn.recommendation.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.bogdsvn.recommendation.dtos.BioDto;
import ru.bogdsvn.recommendation.dtos.PreferenceDto;
import ru.bogdsvn.recommendation.dtos.ProfileDto;
import ru.bogdsvn.recommendation.dtos.ResultDto;
import ru.bogdsvn.recommendation.services.grpc.GrpcProfileClientService;
import ru.bogdsvn.recommendation.services.grpc.GrpcProximityClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class RecommendationService {
    private List<ProfileDto> nearbyProfiles;
    private PreferenceDto userPreference;

    private final GrpcProximityClientService grpcProximityClientService;
    private final GrpcProfileClientService grpcProfileClientService;

    public List<ResultDto> getRecommendation(long id) {
        nearbyProfiles = new ArrayList<>(50);
        userPreference = new PreferenceDto();

        Thread thread1 = new Thread(() -> userPreference = grpcProfileClientService.getPreference(id));
        Thread thread2 = new Thread(() -> nearbyProfiles = grpcProximityClientService.getNearbyProfiles(id));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            return filterProfiles();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        throw new RuntimeException("Recommendation failed");
    }

    private List<ResultDto> filterProfiles() {
        return nearbyProfiles
                .stream()
                .map(p -> {
                    BioDto bio = grpcProfileClientService.getBio(p.getId());
                    return ResultDto.builder()
                            .age(bio.getAge())
                            .name(bio.getName())
                            .description(bio.getDescription())
                            .gender(bio.getGender())
                            .dist(p.getDist())
                            .build();
                })
                .filter(rd ->
                        rd.getGender().equals(userPreference.getGender()) &&
                        (userPreference.getAgeLowerBound() <= rd.getAge() && rd.getAge() <= userPreference.getAgeUpperBound())
                ).collect(Collectors.toList());
    }
}
