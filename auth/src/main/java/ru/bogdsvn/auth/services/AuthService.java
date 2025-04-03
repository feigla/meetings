package ru.bogdsvn.auth.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bogdsvn.auth.dtos.JwtAuthResponseDto;
import ru.bogdsvn.auth.dtos.SignInDto;
import ru.bogdsvn.auth.dtos.SignUpDto;
import ru.bogdsvn.auth.dtos.UpgradedPasswordDto;
import ru.bogdsvn.auth.errors.BadRequestException;
import ru.bogdsvn.auth.store.entities.UserEntity;
import ru.bogdsvn.auth.utils.Role;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthResponseDto signUp(SignUpDto request) {
        var user = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userService.createUser(user);

        String refreshToken = jwtService.generateRefreshToken(user);
        String accessToken = jwtService.generateAccessToken(user);

        return JwtAuthResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return token if password is correct otherwise null
     */
    public JwtAuthResponseDto signIn(SignInDto request) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));
            if (auth.isAuthenticated()) {
                var user = userService
                        .userDetailsService()
                        .loadUserByUsername(request.getUsername());

                String refreshToken = jwtService.generateRefreshToken(user);
                String accessToken = jwtService.generateAccessToken(user);

                return JwtAuthResponseDto.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
            }
        } catch (Exception e) {
        }
        throw new AccessDeniedException("Доступ запрещен");
    }

    /**
     * Обновления токенов
     * @param accessToken
     * @param refreshToken
     * @return JwtAuthResponseDto, в котором хранятся refresh и access токены
     */
    public JwtAuthResponseDto refresh(String accessToken, String refreshToken) {
        if (!jwtService.isAccessTokenValid(accessToken) &&
                jwtService.isRefreshTokenValid(refreshToken)) {
            String username = jwtService.extractUsernameFromRefreshToken(refreshToken);

            var user = userService
                    .userDetailsService()
                    .loadUserByUsername(username);

            String newRefreshToken = jwtService.generateRefreshToken(user);
            String newAccessToken = jwtService.generateAccessToken(user);

            return JwtAuthResponseDto.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .build();
        }
        throw new BadRequestException("Токены не валидны");
    }

    /**
     * Смена пароля пользователю
     *
     * @param request данные пользователя
     * @return token if password is correct otherwise null
     */
    public JwtAuthResponseDto resetPassword(UpgradedPasswordDto request) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getOldPassword()
            ));
            if (auth.isAuthenticated()) {
                var user = userService
                        .userDetailsService()
                        .loadUserByUsername(request.getUsername());

                userService.reset(request.getUsername(), passwordEncoder.encode(request.getNewPassword()));

                String refreshToken = jwtService.generateRefreshToken(user);
                String accessToken = jwtService.generateAccessToken(user);

                return JwtAuthResponseDto.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
            }
        } catch (Exception e) {
        }
        throw new AccessDeniedException("Доступ запрещен");
    }

}