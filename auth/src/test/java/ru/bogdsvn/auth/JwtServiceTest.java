package ru.bogdsvn.auth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bogdsvn.auth.services.JwtService;
import ru.bogdsvn.auth.store.entities.UserEntity;
import ru.bogdsvn.auth.utils.Role;

@SpringBootTest
class JwtServiceTest {

    @Autowired
    JwtService jwtService;

    @Test
    void givenToken_shouldReturnVersion() {
        var user = UserEntity.builder()
                .username("anyway")
                .password("anyway")
                .role(Role.USER)
                .build();

        String token = jwtService.generateRefreshToken(user);

        long version = jwtService.extractVersionFromRefreshToken(token);

        Assertions.assertEquals(Long.MIN_VALUE, version);
    }

}
