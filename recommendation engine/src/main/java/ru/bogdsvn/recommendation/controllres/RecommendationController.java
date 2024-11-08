package ru.bogdsvn.recommendation.controllres;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.bogdsvn.recommendation.dtos.BioDto;
import ru.bogdsvn.recommendation.dtos.ProfileDto;
import ru.bogdsvn.recommendation.services.RecommendationService;
import ru.bogdsvn.recommendation.services.grpc.GrpcProximityClientService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RecommendationController {
    private final static String GET_RECOMMENDATION = "/api/v1/recommendations";

    private final RecommendationService recommendationService;

    @GetMapping(GET_RECOMMENDATION)
    public List<BioDto> getRecommendation(@RequestHeader("loggedId") String id) {
        return recommendationService.getRecommendation(id);
    }
}
