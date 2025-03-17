package ru.bogdsvn.kafka_library.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bogdsvn.kafka_library.utils.Status;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProcessedLocationEvent {
    private Long id;
    private Status status;
}
