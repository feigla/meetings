package ru.bogdsvn.recommendation.services.integrations;

import net.devh.boot.grpc.client.autoconfigure.GrpcClientAutoConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.recommendation.dtos.ResultDto;
import ru.bogdsvn.recommendation.factories.ResultFactory;
import ru.bogdsvn.recommendation.services.RecommendationService;
import ru.bogdsvn.recommendation.store.ViewedProfileEmbeddedId;
import ru.bogdsvn.recommendation.store.entities.ViewedProfileEntity;
import ru.bogdsvn.recommendation.store.repositories.ViewedProfileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
@ImportAutoConfiguration(exclude = GrpcClientAutoConfiguration.class)
public class RecommendationServiceTest {
    @SpyBean
    private RecommendationService recommendationService;

    @Autowired
    private ViewedProfileRepository viewedProfileRepository;

    @Autowired
    private ResultFactory resultFactory;

    void setUpFiftyProfiles() {
        List<ViewedProfileEntity> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(ViewedProfileEntity.builder()
                    .id(ViewedProfileEmbeddedId.builder()
                            .profileId(1L)
                            .viewedProfileId(2L + i)
                            .build())
                    .gender("MALE")
                    .age(18 + i)
                    .name(String.valueOf(i))
                    .description("I'm " + (i + 1))
                    .dist(100. + i)
                    .build());
        }
        viewedProfileRepository.saveAll(list);
    }

    @Test
    void givenProfileId_shouldReturnRecommendations() {
        Mockito.doReturn(List.of(
                        ResultDto.builder()
                                .id(2L)
                                .gender("MALE")
                                .age(19)
                                .name("Oleg")
                                .description("I'm 1 man")
                                .dist(100.1)
                                .build(),
                        ResultDto.builder()
                                .id(3L)
                                .gender("MALE")
                                .age(20)
                                .name("Vladislav")
                                .description("I'm 2 man")
                                .dist(105.1)
                                .build()
                )
        ).when(recommendationService).fetchUsers(1);
        List<ResultDto> list = recommendationService.getRecommendation(1);
        Assertions.assertEquals(2, list.size());
    }

    @Test
    void givenProfileId_shouldReturnRecommendationsAndSaveOthers() {
        List<ResultDto> mk = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mk.add(ResultDto.builder()
                    .id(2L + i)
                    .gender("MALE")
                    .age(18 + i)
                    .name(String.valueOf(i))
                    .description("I'm " + i)
                    .dist(100. + i)
                    .build()
            );
        }
        Mockito.doReturn(mk).when(recommendationService).fetchUsers(1);
        List<ResultDto> recommendations = recommendationService.getRecommendation(1);
        Assertions.assertEquals(5, recommendations.size());
        for (int i = 0; i < 5; i++) {
            Assertions.assertTrue(mk.get(i).equals(recommendations.get(i)));
        }
        List<ResultDto> viewedProfiles = viewedProfileRepository.findAll().stream()
                .map(resultFactory::makeResultDto)
                .collect(Collectors.toList());
        Assertions.assertEquals(45, viewedProfiles.size());
        for (int i = 5; i < 50; i++) {
            Assertions.assertTrue(mk.get(i).equals(viewedProfiles.get(i - 5)));
        }
    }

    @Test
    void givenProfileId_shouldReturnNextViewedProfileAndDeleteIt() {
        setUpFiftyProfiles();
        List<ResultDto> recommendations = recommendationService.getRecommendation(1);
        Assertions.assertEquals(5, recommendations.size());
        List<ResultDto> viewedProfiles = viewedProfileRepository.findAll().stream()
                .map(resultFactory::makeResultDto)
                .collect(Collectors.toList());
        Assertions.assertEquals(45, viewedProfiles.size());
        for (ResultDto viewedProfile : viewedProfiles) {
            for (ResultDto recommendation : recommendations) {
                Assertions.assertFalse(viewedProfile.equals(recommendation));
            }
        }
    }


    @Transactional
    @BeforeEach
    public void clearDataBase() {
        viewedProfileRepository.deleteAllInBatch();
    }
}
