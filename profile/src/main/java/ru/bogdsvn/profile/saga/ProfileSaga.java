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

    public void startSaga(long id) {
        kafkaTemplate.send(locationCommandTopic, id);
    }

    @KafkaHandler
    private void handleEvent(@Payload ProcessedLocationEvent event) {
        kafkaTemplate.send(recommendationCommandTopic, event.getId());
    }

    @KafkaHandler
    private void handleEvent(@Payload ProcessedRecommendationEvent event) {
        stopSaga(event.getId());
    }

    public void stopSaga(long id) {

    }
}
