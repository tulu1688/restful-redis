package com.vinfast.ecosystem.restfulredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ComponentScan(basePackages = "com.vinfast.ecosystem.restfulredis")
@PropertySource("classpath:/redis.properties")
@SpringBootApplication
public class Application {
    private static final String REDIS_NODE_SEPARATOR = ";";
    private static final String HOST_PORT_SEPARATOR = ":";

    @Autowired
    private RedisConfiguration redisConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        List<RedisNode> redisNodes = Arrays.stream(redisConfiguration.getRedisCluster().split(REDIS_NODE_SEPARATOR)).map(redisHost -> {
            String parts[] = redisHost.trim().split(HOST_PORT_SEPARATOR);
            return new RedisNode(parts[0], Integer.valueOf(parts[1]));
        }).collect(Collectors.toList());

        RedisClusterConfiguration configuration = new RedisClusterConfiguration();
        configuration.setClusterNodes(redisNodes);

        return new JedisConnectionFactory(configuration);
    }

    @Bean
    RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
