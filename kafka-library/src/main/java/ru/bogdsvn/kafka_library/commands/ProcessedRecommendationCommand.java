package ru.bogdsvn.kafka_library.commands;

import lombok.*;
import ru.bogdsvn.kafka_library.utils.Status;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProcessedRecommendationCommand {
    private Long id;
    private Status status;
}
