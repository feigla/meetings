package ru.bogdsvn.redis.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisJwtService {

    private final ValueOperations<String, String> ops;

    public RedisJwtService(@Qualifier("valueOperations") ValueOperations<String, String> ops) {
        this.ops = ops;
    }

    public boolean exist(String token) {
        return Boolean.TRUE.toString().equals(ops.get(token));
    }

    public void add(String token) {
        ops.set(token, Boolean.TRUE.toString(), Duration.ofSeconds(2592000L));
    }
}
