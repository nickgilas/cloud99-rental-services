package com.cloud99.rental.domain.security;

import com.cloud99.rental.domain.MongoDocument;

import org.springframework.data.annotation.Id;

import java.util.Collection;

/**
 * Specifies access levels a user has for a single resource. Permissions are
 * cascade down to all child resources of the "parent" resource of type
 * {@link SecuredResource}.
 *
 */
public class SecurityAccess implements MongoDocument<String> {

	public static enum Access {
		CREATE, READ, UPDATE, DELETE
	}

	@Id
	private String userId;
	private Collection<Access> access;

	@Override
	public String getId() {
		return userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Collection<Access> getAccess() {
		return access;
	}

	public void setAccess(Collection<Access> access) {
		this.access = access;
	}


}
