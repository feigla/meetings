package ru.bogdsvn.gateway.filters;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import ru.bogdsvn.gateway.errors.AccessDeniedException;
import ru.bogdsvn.gateway.errors.UnauthorizedException;
import ru.bogdsvn.gateway.utils.JwtService;

@Log4j2
@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    private final JwtService jwtService;

    public AuthFilter(JwtService jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            String jwt = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (jwt == null || !jwt.startsWith("Bearer ")) {
                throw new AccessDeniedException("Access denied");
            }

            String token = jwt.substring(7);

            if (!jwt.startsWith("Bearer ") || !jwtService.isTokenValid(token)) {
                throw new UnauthorizedException("Jwt is invalid");
            }

            String id = jwtService.extractId(token);

            log.info(id);

            var request = exchange
                    .getRequest()
                    .mutate()
                    .header("loggedId", id)
                    .build();

            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config {

    }
}
