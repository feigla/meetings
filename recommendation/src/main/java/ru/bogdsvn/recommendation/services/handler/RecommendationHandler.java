package ru.bogdsvn.recommendation.services.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.bogdsvn.kafka_library.commands.ProcessedRecommendationCommand;
import ru.bogdsvn.kafka_library.events.ProcessedRecommendationEvent;
import ru.bogdsvn.recommendation.services.RecommendationService;

@RequiredArgsConstructor
@Component
@KafkaListener(topics = {
        "${recommendation.command.topic}"
})
public class RecommendationHandler {
    @Value("${recommendation.event.topic}")
    private String recommendationEventTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final RecommendationService recommendationService;

    @KafkaHandler
    public void handleCommand(@Payload ProcessedRecommendationCommand command) {
        recommendationService.deleteViewedProfilesById(command.getId());
        kafkaTemplate.send(
                recommendationEventTopic,
                ProcessedRecommendationEvent.builder()
                        .id(command.getId())
                        .status(command.getStatus())
                        .build()
        );
    }
}
