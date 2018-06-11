package com.cloud99.rental.domain.security;

import com.cloud99.rental.domain.security.SecurityAccess.Access;

import java.util.Collection;

/**
 * This interfaces is to be added to any resources found within the
 * <code>com.cloud99.rental.domain</code> package that requires security access
 */
public interface SecuredResource {

	Collection<Access> getSecurityAccessForUser(User user);

	default boolean canUserUpdateResource(User user) {

		return canUserAccess(Access.UPDATE, getSecurityAccessForUser(user));
	}

	default boolean canUserReadResource(User user) {

		return canUserAccess(Access.READ, getSecurityAccessForUser(user));
	}

	default boolean canUserCreateResource(User user) {

		return canUserAccess(Access.CREATE, getSecurityAccessForUser(user));
	}

	default boolean canUserDeleteResource(User user) {

		return canUserAccess(Access.DELETE, getSecurityAccessForUser(user));
	}

	default boolean canUserAccess(Access requestedAccess, Collection<Access> assignedAccess) {

		for (Access access : assignedAccess) {
			if (access.equals(requestedAccess)) {
				return true;
			}
		}
		return false;
	}

	void addSecurityAccess(SecurityAccess securityAccess);
}
