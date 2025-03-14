package ru.bogdsvn.location.services.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.bogdsvn.kafka_library.commands.ProcessedLocationCommand;
import ru.bogdsvn.kafka_library.events.ProcessedLocationEvent;
import ru.bogdsvn.location.services.LocationService;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = {
        "${location.command.topic}"
})
public class LocationHandler {
    @Value("${location.event.topic}")
    private String locationEventTopic;

    private final LocationService locationService;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaHandler
    public void handleCommand(@Payload ProcessedLocationCommand command) {
        locationService.setActive(command.getId());
        kafkaTemplate.send(
                locationEventTopic,
                ProcessedLocationEvent.builder()
                        .id(command.getId())
                        .build()
        );
    }
}
