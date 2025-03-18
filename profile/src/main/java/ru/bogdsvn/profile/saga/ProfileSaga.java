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
import ru.bogdsvn.profile.services.BioService;
import ru.bogdsvn.profile.store.entites.BioEntity;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = {
        "${recommendation.event.topic}",
        "${location.event.topic}"
})
public class ProfileSaga {
    @Value("${location.command.topic}")
    private String locationCommandTopic;

    @Value("${recommendation.command.topic}")
    private String recommendationCommandTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final BioService bioService;

    public void startSaga(long id, Status status) {
        kafkaTemplate.send(
                locationCommandTopic,
                ProcessedLocationCommand.builder()
                        .id(id)
                        .status(status)
                        .build()
        );
    }

    @KafkaHandler
    private void handleEvent(@Payload ProcessedLocationEvent event) {
        if (event.getStatus().equals(Status.DEACTIVATE_PROCESSED)) {
            kafkaTemplate.send(
                    recommendationCommandTopic,
                    ProcessedRecommendationCommand.builder()
                            .id(event.getId())
                            .status(event.getStatus())
                            .build()
            );
        } else {
            bioService.setStatus(event.getId(), Status.ACTIVATED);
        }
    }

    @KafkaHandler
    private void handleEvent(@Payload ProcessedRecommendationEvent event) {
        bioService.setStatus(event.getId(), Status.DEACTIVATED);
    }
}
