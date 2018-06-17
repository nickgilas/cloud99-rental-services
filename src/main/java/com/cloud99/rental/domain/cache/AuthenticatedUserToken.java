package com.cloud99.rental.domain.cache;

import com.cloud99.rental.domain.document.security.User;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@RedisHash("AuthenticatedUserToken")
public class AuthenticatedUserToken {
	private static final int EXPIRATION = 60 * 24;

	@Id
	private String id;

	private final String token;

	private final User user;

	private final LocalDateTime expiryDate;

	public AuthenticatedUserToken(User user) {
		expiryDate = LocalDateTime.now().minusMinutes(EXPIRATION);
		token = UUID.randomUUID().toString();
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public User getUser() {
		return user;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

}
