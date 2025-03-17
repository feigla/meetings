package ru.bogdsvn.kafka_library.events;

import lombok.*;
import ru.bogdsvn.kafka_library.utils.Status;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProcessedRecommendationEvent {
    private Long id;
    private Status status;
}
