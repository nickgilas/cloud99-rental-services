package com.cloud99.rental.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@ComponentScan(basePackages = { "com.cloud99.rental" })
public class RedisConfig {

	@Value("${redis.user.name}")
	private String redisUserName;

	@Value("${redis.user.password}")
	private String redisPassword;

	@Value("${redis.host}")
	private String host;

	@Value("${redis.port}")
	private String port;

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
