package ru.bogdsvn.redis;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan("ru.bogdsvn.redis.services")
@ComponentScan("ru.bogdsvn.redis.configurations")
public class EnableRedis {
}
