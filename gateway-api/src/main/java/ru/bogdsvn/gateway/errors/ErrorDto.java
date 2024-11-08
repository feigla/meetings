package ru.bogdsvn.gateway.errors;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private String error;
    private String message;
}
