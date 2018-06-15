package com.cloud99.rental.domain.redis;

import com.cloud99.rental.domain.security.User;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("AuthToken")
public class AuthToken {

	private String token;
	private User user;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
