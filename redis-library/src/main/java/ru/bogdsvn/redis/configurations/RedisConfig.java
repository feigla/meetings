package ru.bogdsvn.redis.configurations;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${redis.database:-1}")
    Integer redisDatabase;

    @Value("${redis.password:}")
    String redisPassword;

    @Value("${redis.host}")
    String host;

    @Value("${redis.port}")
    Integer port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);

        if (!redisPassword.trim().isEmpty()) {
            redisStandaloneConfiguration.setPassword(redisPassword);
        }

        if (redisDatabase > 0) {
            redisStandaloneConfiguration.setDatabase(redisDatabase);
        }

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public <K, V> RedisTemplate<K, V> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        final RedisTemplate<K, V> redisTemplate = new RedisTemplate<>();


        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(makeDefaultObjectMapper()));
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    public <K, V> HyperLogLogOperations<K, V> hyperLogLogOperations(@Qualifier("redisTemplate") RedisTemplate<K, V> redisTemplate) {
        return redisTemplate.opsForHyperLogLog();
    }

    @Bean
    public <K, HK, V> HashOperations<K, HK, V> hashOperations(@Qualifier("redisTemplate") RedisTemplate<K, V> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public <K, V> ZSetOperations<K, V> zSetOperations(@Qualifier("redisTemplate") RedisTemplate<K, V> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

    @Bean
    public <K, V> ClusterOperations<K, V> clusterOperations(@Qualifier("redisTemplate") RedisTemplate<K, V> redisTemplate) {
        return redisTemplate.opsForCluster();
    }

    @Bean
    public <K, V> GeoOperations<K, V> geoOperations(@Qualifier("redisTemplate") RedisTemplate<K, V> redisTemplate) {
        return redisTemplate.opsForGeo();
    }

    @Bean
    public <K, V> ListOperations<K, V> listOperations(@Qualifier("redisTemplate") RedisTemplate<K, V> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public <K, V> SetOperations<K, V> setOperations(@Qualifier("redisTemplate") RedisTemplate<K, V> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public <K, HK, V> StreamOperations<K, HK, V> streamOperations(@Qualifier("redisTemplate") RedisTemplate<K, V> redisTemplate) {
        return redisTemplate.opsForStream();
    }

    @Bean
    public <K, V> ValueOperations<K, V> valueOperations(@Qualifier("redisTemplate") RedisTemplate<K, V> redisTemplate) {
        return redisTemplate.opsForValue();
    }


    private static ObjectMapper makeDefaultObjectMapper() {

        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());

        mapper.activateDefaultTyping(
                mapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        return mapper;
    }
}