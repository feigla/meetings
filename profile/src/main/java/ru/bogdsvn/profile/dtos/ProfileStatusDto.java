package ru.bogdsvn.profile.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bogdsvn.kafka_library.utils.Status;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProfileStatusDto {
    private Status status;
}
