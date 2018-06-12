package com.cloud99.rental.config.security;

import com.cloud99.rental.domain.security.User;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class VerificationToken {
	private static final int EXPIRATION = 60 * 24;

	@Id
	private String id;

	private final String token;

	private final User user;

	private final LocalDateTime expiryDate;

	public VerificationToken(User user) {
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
