package ru.bogdsvn.auth.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bogdsvn.auth.dtos.JwtAuthResponseDto;
import ru.bogdsvn.auth.dtos.SignInDto;
import ru.bogdsvn.auth.dtos.SignUpDto;
import ru.bogdsvn.auth.services.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final static String SIGN_UP = "/api/v1/auth/sign-up";
    private final static String SIGN_IN = "/api/v1/auth/sign-in";

    private final AuthService authenticationService;

    @PostMapping(SIGN_UP)
    public JwtAuthResponseDto signUp(@RequestBody @Valid SignUpDto request) {
        return authenticationService.signUp(request);
    }

    @PostMapping(SIGN_IN)
    public JwtAuthResponseDto signIn(@RequestBody @Valid SignInDto request) {
        return authenticationService.signIn(request);
    }
}
