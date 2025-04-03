package ru.bogdsvn.auth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.bogdsvn.auth.store.entities.UserEntity;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${token.signing.access-token}")
    private String accessKey;
    @Value("${token.signing.refresh-token}")
    private String refreshKey;

    @Value("${token.expiration.refresh-token}")
    private long refreshExpirations;
    @Value("${token.expiration.access-token}")
    private long accessExpiration;

    /**
     * Генерация refresh токена
     *
     * @param userDetails данные пользователя
     * @return токен
     */
    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof UserEntity customUserDetails) {
            claims.put("id", customUserDetails.getId());
            claims.put("version", customUserDetails.getVersion());
        }
        return generateToken(claims, userDetails, refreshExpirations, getSigningKey(refreshKey));
    }

    /**
     * Генерация access токена
     *
     * @param userDetails данные пользователя
     * @return токен
     */
    public String generateAccessToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof UserEntity customUserDetails) {
            claims.put("id", customUserDetails.getId());
            claims.put("role", customUserDetails.getRole());
        }
        return generateToken(claims, userDetails, accessExpiration, getSigningKey(accessKey));
    }

    /**
     * Генерация токена
     *
     * @param extraClaims дополнительные данные
     * @param userDetails данные пользователя
     * @param expiration время жизни токена
     * @return токен
     */
    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, Long expiration, SecretKey key) {
        return Jwts.builder()
                .claims(extraClaims)
                .signWith(key, Jwts.SIG.HS256)
                .subject(userDetails.getUsername())
                .id(String.valueOf(((UserEntity) userDetails).getId()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
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
        try {
            return Jwts
                    .parser()
                    .verifyWith(getSigningKey(key))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
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
     * Извлечение username пользователя из refresh токена
     *
     * @param token токен
     * @return username
     */
    public String extractUsernameFromRefreshToken(String token) {
        return String.valueOf(extractClaim(token, Claims::getSubject, refreshKey));
    }
}
