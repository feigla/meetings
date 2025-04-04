package ru.bogdsvn.gateway.filters;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Component;
import ru.bogdsvn.gateway.errors.AccessDeniedException;
import ru.bogdsvn.gateway.errors.AuthorizationException;
import ru.bogdsvn.gateway.errors.BadRequestException;
import ru.bogdsvn.gateway.utils.JwtService;
import ru.bogdsvn.redis.services.RedisJwtService;

import java.util.List;

@Log4j2
@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    private final JwtService jwtService;
    private final RedisJwtService redisJwtService;

    public AuthFilter(JwtService jwtService, RedisJwtService redisJwtService) {
        super(Config.class);
        this.jwtService = jwtService;
        this.redisJwtService = redisJwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            List<HttpCookie> refreshTokenCookie = exchange.getRequest().getCookies().get("refresh_token");
            String refreshToken = null;

            if (refreshTokenCookie != null && !refreshTokenCookie.isEmpty()) {
                refreshToken = refreshTokenCookie.get(0).getValue();
            }

            if (refreshToken == null) {
                throw new BadRequestException("Refresh токен передан неверно");
            }

            String jwt = exchange.getRequest().getHeaders().getFirst("Authorization");

            if (jwt == null || !jwt.startsWith("Bearer ")) {
                throw new BadRequestException("Access токен передан неверно");
            }

            String accessToken = jwt.substring(7);

            if (redisJwtService.exist(refreshToken)) {
                throw new AccessDeniedException("Доступ запрещен");
            }

            if (!jwtService.isAccessTokenValid(accessToken)) {
                throw new AuthorizationException("Истекло время хранения access токена");
            }

            long id = jwtService.extractIdFromAccessToken(accessToken);

            var request = exchange
                    .getRequest()
                    .mutate()
                    .header("loggedId", String.valueOf(id))
                    .build();

            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config {

    }
}
