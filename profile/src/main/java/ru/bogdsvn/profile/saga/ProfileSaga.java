package ru.bogdsvn.profile.saga;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.bogdsvn.kafka_library.commands.ProcessedLocationCommand;
import ru.bogdsvn.kafka_library.commands.ProcessedRecommendationCommand;
import ru.bogdsvn.kafka_library.events.ProcessedLocationEvent;
import ru.bogdsvn.kafka_library.events.ProcessedRecommendationEvent;
import ru.bogdsvn.kafka_library.utils.Status;
import ru.bogdsvn.profile.services.DeactivatedProfileService;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = {
        "${recommendation.event.topic}",
        "${location.event.topic}"
})
public class DeactivatedProfileSaga {
    @Value("${location.command.topic}")
    private String locationCommandTopic;

    @Value("${recommendation.command.topic}")
    private String recommendationCommandTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final DeactivatedProfileService deactivatedProfileService;

    public void startSaga(long id) {
        kafkaTemplate.send(
                locationCommandTopic,
                ProcessedLocationCommand.builder()
                        .id(id)
                        .status(Status.DEACTIVATE_PROCESSED)
                        .build()
        );
    }

    @KafkaHandler
    private void handleEvent(@Payload ProcessedLocationEvent event) {
        kafkaTemplate.send(
                recommendationCommandTopic,
                ProcessedRecommendationCommand.builder()
                        .id(event.getId())
                        .build()
        );
    }

    @KafkaHandler
    private void handleEvent(@Payload ProcessedRecommendationEvent event) {
        deactivatedProfileService.setStatusFinished(event.getId());
    }
}
