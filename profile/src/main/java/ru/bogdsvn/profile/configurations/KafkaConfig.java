package ru.bogdsvn.profile.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public NewTopic createProfileEventsTopic() {
        return TopicBuilder.name("recommendation-service")
                .partitions(5)
                .replicas(2)
                .build();
    }

    @Bean
    public NewTopic createLocationServiceTopic() {
        return TopicBuilder.name("location-service")
                .partitions(5)
                .replicas(2)
                .build();
    }

    @Bean
    public NewTopic createProfileServiceTopic() {
        return TopicBuilder.name("profile-service")
                .partitions(5)
                .replicas(2)
                .build();
    }
}
