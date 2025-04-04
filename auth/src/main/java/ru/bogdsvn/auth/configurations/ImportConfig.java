package ru.bogdsvn.auth.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.bogdsvn.redis.EnableRedis;

@Import({
        EnableRedis.class
})
@Configuration
public class ImportConfig {
}
