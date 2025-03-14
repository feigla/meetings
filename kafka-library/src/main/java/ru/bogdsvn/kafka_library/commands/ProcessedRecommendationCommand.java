package ru.bogdsvn.kafka_library.commands;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProcessedRecommendationCommand {
    private Long id;
}
