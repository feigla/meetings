package ru.bogdsvn.gateway.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${token.signing.access-token}")
    private String accessKey;
    @Value("${token.signing.refresh-token}")
    private String refreshKey;

    /**
     * Проверка refresh токена на валидность
     *
     * @param token       токен
     * @return true, если токен валиден
     */
    public boolean isRefreshTokenValid(String token) {
        return !isTokenExpired(token, refreshKey);
    }

    /**
     * Проверка access токена на валидность
     *
     * @param token       токен
     * @return true, если токен валиден
     */
    public boolean isAccessTokenValid(String token) {
        return !isTokenExpired(token, accessKey);
    }

    /**
     * Проверка токена на просроченность
     *
     * @param token токен
     * @return true, если токен просрочен
     */
    private boolean isTokenExpired(String token, String key) {
        return extractExpiration(token, key).before(new Date());
    }

    /**
     * Извлечение даты истечения токена
     *
     * @param token токен
     * @return дата истечения
     */
    private Date extractExpiration(String token, String key) {
        return extractClaim(token, Claims::getExpiration, key);
    }

    /**
     * Извлечение всех данных из токена
     *
     * @param token токен
     * @return данные
     */
    private Claims extractAllClaims(String token, String key) {
        return Jwts
                    .parser()
                    .verifyWith(getSigningKey(key))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }

    /**
     * Извлечение данных из токена
     *
     * @param token           токен
     * @param claimsResolvers функция извлечения данных
     * @param <T>             тип данных
     * @return данные
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers, String key) {
        final Claims claims = extractAllClaims(token, key);
        return claimsResolvers.apply(claims);
    }

    /**
     * Извлечение unique пользователя из access токена
     *
     * @param token токен
     * @return id
     */
    public Long extractIdFromAccessToken(String token) {
        return Long.valueOf(extractClaim(token, Claims::getId, accessKey));
    }


    /**
     * Получение ключа для подписи токена
     *
     * @return ключ
     */
    private SecretKey getSigningKey(String key) {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
