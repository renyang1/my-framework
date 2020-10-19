package com.ry.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置。定义Redis的序列化，key采用String，value采用json
 * 
 * @author cobe
 *
 */
@Configuration
@EnableConfigurationProperties({RedisProperties.class})
@ConditionalOnProperty({"spring.redis.host", "spring.redis.port"})
public class RedisConfig extends CachingConfigurerSupport {

    @Primary
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer =
            new GenericJackson2JsonRedisSerializer();

        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(genericJackson2JsonRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Primary
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    @Primary
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory factory) {
        return RedisCacheManager.create(factory);
    }

}