package ru.bogdsvn.auth.errors;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            AccessDeniedException.class,
            JwtException.class
    })
    public ResponseEntity<ErrorDto> handleForbiddenException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ErrorDto
                        .builder()
                        .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                        .message(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationException(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorDto.builder()
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .message(ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage())
                        .build()
                );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDto> handleBadRequestException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorDto.builder()
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .message(ex.getMessage())
                        .build()
                );
    }
}
