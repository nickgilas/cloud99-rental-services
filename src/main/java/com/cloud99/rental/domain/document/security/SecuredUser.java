package com.cloud99.rental.domain.document.security;

import com.cloud99.rental.config.security.SecurityRole;
import com.cloud99.rental.domain.document.account.Feature;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecuredUser implements UserDetails, Authentication {
	private static final long serialVersionUID = -2910384487535415883L;
	
	private final User user;
	private final List<Feature> assignedFeatures;
	private final Collection<SecurityRole> securityRoles;
	private boolean isAuthenticated = false;
	
	public SecuredUser(User user, List<Feature> assignedFeatures, Collection<SecurityRole> collection,
			boolean authenticated) {
		super();
		this.user = user;
		this.assignedFeatures = assignedFeatures;
		this.securityRoles = collection;
		this.isAuthenticated = authenticated;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return convertSecurityRolesToGrantedAuthorities(user.getSecurityRoles());
	}

	public static List<GrantedAuthority> convertSecurityRolesToGrantedAuthorities(Collection<SecurityRole> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (SecurityRole role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.name()));
		}
		return authorities;
	}

	public Collection<SecurityRole> getSecurityRoles() {
		return securityRoles;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	public User getUser() {
		return user;
	}

	public List<Feature> getAssignedFeatures() {
		return assignedFeatures;
	}

	@Override
	public String getName() {
		return user.getPerson().getEmail();
	}

	@Override
	public Object getCredentials() {
		return assignedFeatures;
	}

	@Override
	public Object getDetails() {
		// TODO - Not sure what this should be but not a user im sure
		return user;
	}

	@Override
	public Object getPrincipal() {
		return user;
	}

	@Override
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated;
	}

}
