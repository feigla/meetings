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
    private void handleEvent(@Payload ProcessedLocationCommand command) {
        kafkaTemplate.send(recommendationCommandTopic, command.getId());
    }

    @KafkaHandler
    private void handleEvent(@Payload ProcessedRecommendationCommand command) {
        stopSaga(command.getId());
    }

    public void stopSaga(long id) {

    }
}
