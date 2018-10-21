package com.vinfast.ecosystem.restfulredis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfiguration {

    @Value("${redis.cluster}")
    private String redisCluster;
    @Value("${redis.time_to_live}")
    private int timeToLive;

    public String getRedisCluster() {
        return redisCluster;
    }

    public int getTimeToLive() {
        return timeToLive;
    }
}
