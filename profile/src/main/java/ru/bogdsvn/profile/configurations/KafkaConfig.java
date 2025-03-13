package ru.bogdsvn.profile.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {
    @Value("${location.command.topic}")
    private String locationCommandTopic;

    @Value("${recommendation.command.topic}")
    private String recommendationCommandTopic;

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public NewTopic createLocationCommandTopic() {
        return TopicBuilder.name(locationCommandTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic createRecommendationCommandTopic() {
        return TopicBuilder.name(recommendationCommandTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
