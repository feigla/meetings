package ru.bogdsvn.kafka_library.events;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProcessedRecommendationEvent {
    private Long id;
}
