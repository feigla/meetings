package ru.bogdsvn.profile.saga;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = {
        "${recommendation.command.topic}"
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
    public void handleEvent(@Payload Object payload) {
        kafkaTemplate.send(recommendationCommandTopic, payload);
    }

    @KafkaHandler
    public void handleEvent(@Payload String k) {

    }
}
