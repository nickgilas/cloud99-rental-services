package com.cloud99.rental.domain.security;

import com.cloud99.rental.domain.account.Feature;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecuredUser implements UserDetails {
	private static final long serialVersionUID = -2910384487535415883L;
	
	private User user;
	private List<Feature> assignedFeatures;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
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

	public void setUser(User user) {
		this.user = user;
	}

	public List<Feature> getAssignedFeatures() {
		return assignedFeatures;
	}

	public void setAssignedFeatures(List<Feature> assignedFeatures) {
		this.assignedFeatures = assignedFeatures;
	}


}
