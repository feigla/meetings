package ru.bogdsvn.auth.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bogdsvn.auth.dtos.JwtAuthResponseDto;
import ru.bogdsvn.auth.dtos.SignInDto;
import ru.bogdsvn.auth.dtos.SignUpDto;
import ru.bogdsvn.auth.dtos.UpgradedPasswordDto;
import ru.bogdsvn.auth.errors.BadRequestException;
import ru.bogdsvn.auth.services.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final static String SIGN_UP = "/api/v1/auth/sign-up";
    private final static String SIGN_IN = "/api/v1/auth/sign-in";
    private final static String REFRESH_TOKEN = "/api/v1/auth/refresh-tokens";
    private final static String RESET_PASSWORD = "/api/v1/auth/reset-password";
    private final static String LOG_OUT = "/api/v1/auth/logout";

    private final AuthService authenticationService;

    @PostMapping(SIGN_UP)
    public JwtAuthResponseDto signUp(@RequestBody @Valid SignUpDto request) {
        return authenticationService.signUp(request);
    }

    @PostMapping(SIGN_IN)
    public JwtAuthResponseDto signIn(@RequestBody @Valid SignInDto request) {
        return authenticationService.signIn(request);
    }

    @PostMapping(REFRESH_TOKEN)
    public JwtAuthResponseDto refresh(@RequestHeader("Authorization") String jwt,
                                     @CookieValue("refresh_token") String refreshToken) {
        if (refreshToken == null || jwt == null || !jwt.startsWith("Bearer ")) {
            throw new BadRequestException("Токены не валидны");
        }
        return authenticationService.refresh(jwt.substring(7), refreshToken);
    }

    @PostMapping(RESET_PASSWORD)
    public JwtAuthResponseDto resetPassword(@RequestBody @Valid UpgradedPasswordDto request) {
        return authenticationService.resetPassword(request);
    }

    @PostMapping(LOG_OUT)
    public ResponseEntity<Void> logout(@CookieValue("refresh_token") String refreshToken) {
        authenticationService.addTokenInBlackList(refreshToken);
        return ResponseEntity.noContent().build();
    }
}
