package com.msa.banking.product.config.redis;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(defaultCacheConfiguration())
                .withInitialCacheConfigurations(customConfigurationMap())
                .build();
    }

    /**
     * 새로운 설정 추가 하는 곳
     * @return
     */
    private Map<String, RedisCacheConfiguration> customConfigurationMap() {
        Map<String, RedisCacheConfiguration> customConfigurationMap = new HashMap<>();

        customConfigurationMap.put(RedisCacheKey.pdfCache, PdfCacheConfiguration());
        customConfigurationMap.put(RedisCacheKey.ProductListCache, ProductListCacheConfiguration());
        customConfigurationMap.put(RedisCacheKey.ProductDetailCache, ProductDetailCacheConfiguration());

        return customConfigurationMap;
    }

    /**
     * 기본 설정
     * @return
     */
    private RedisCacheConfiguration defaultCacheConfiguration() {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()// 널은 캐싱 안함
                .entryTtl(Duration.ofSeconds(120))// 120초간 캐시 데이터 유지
                .computePrefixWith(CacheKeyPrefix.simple())
                .serializeValuesWith(
                        SerializationPair.fromSerializer(RedisSerializer.java())
                );
    }

    /**
     * PdfCache의 설정
     */
    private RedisCacheConfiguration PdfCacheConfiguration() {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()// 널은 캐싱 안함
                .entryTtl(Duration.ofSeconds(60*60))// 1시간
                .computePrefixWith(CacheKeyPrefix.simple())
                .serializeValuesWith(
                        SerializationPair.fromSerializer(RedisSerializer.java())
                );
    }

    /**
     * 싱품 목록 캐시 설정
     */
    private RedisCacheConfiguration ProductListCacheConfiguration() {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()// 널은 캐싱 안함
                .entryTtl(Duration.ofSeconds(6*60*60))// 6시간
                .computePrefixWith(CacheKeyPrefix.simple())
                .serializeValuesWith(
                        SerializationPair.fromSerializer(RedisSerializer.java())
                );
    }

    private RedisCacheConfiguration ProductDetailCacheConfiguration() {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()// 널은 캐싱 안함
                .entryTtl(Duration.ofSeconds(6*60))// 1시간
                .computePrefixWith(CacheKeyPrefix.simple())
                .serializeValuesWith(
                        SerializationPair.fromSerializer(RedisSerializer.java())
                );
    }
}
