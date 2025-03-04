package ru.bogdsvn.recommendation.services.integrations;

import net.devh.boot.grpc.client.autoconfigure.GrpcClientAutoConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.bogdsvn.recommendation.dtos.ResultDto;
import ru.bogdsvn.recommendation.services.RecommendationService;

import java.util.List;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
@ImportAutoConfiguration(exclude = GrpcClientAutoConfiguration.class)
public class RecommendationServiceTest {
    @SpyBean
    private RecommendationService recommendationService;

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
}
