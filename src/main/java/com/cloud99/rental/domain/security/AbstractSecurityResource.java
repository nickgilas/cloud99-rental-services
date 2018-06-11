package com.cloud99.rental.domain.security;

import com.cloud99.rental.domain.MongoDocument;
import com.cloud99.rental.domain.security.SecurityAccess.Access;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractSecurityResource implements SecuredResource, MongoDocument<String> {

	private List<SecurityAccess> access = new ArrayList<>(2);


	public Collection<Access> getSecurityAccessForUser(User user) {

		for (SecurityAccess sec : this.access) {
			if (sec.getUserId().equals(user.getId())) {
				return sec.getAccess();
			}
		}
		return null;
		
	}

	@Override
	public void addSecurityAccess(SecurityAccess securityAccess) {
		access.add(securityAccess);
	}

}
