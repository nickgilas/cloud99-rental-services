package com.cloud99.rental.config.security;

import org.springframework.security.core.GrantedAuthority;

public enum SecurityRole implements GrantedAuthority {

	USER, ADMIN;

	@Override
	public String getAuthority() {
		return this.name();
	}
}
