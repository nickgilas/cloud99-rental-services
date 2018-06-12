package com.cloud99.rental.service;

import com.cloud99.rental.config.security.SecurityRole;
import com.cloud99.rental.domain.security.User;
import com.cloud99.rental.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements RentalService, UserDetailsService, AuthenticationProvider {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private MessageSource messages;

	// @Autowired
	// private JavaMailSender mailSender;

	// http://www.baeldung.com/registration-verify-user-by-email

	@Transactional
//	@Override
	public User create(User user, HttpServletRequest request) {

		if (emailExist(user.getPerson().getEmail())) {
			throw new ServiceException("email.exists");
		}
		// encode password
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		user.addSecurityRole(SecurityRole.USER);
		
		// TODO - NG - finish implementing event notification for new user created (to generate account validation email)
		String appUrl = request.getContextPath();
		// eventPublisher.publishEvent(new OnRegistrationCompleteEvent(document,
		// request.getLocale(), appUrl));
		
		return userRepo.save(user);

		// TODO - NG - decrypt password - BCrypt.checkpw
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		UserDetails user = loadUserByUsername(name);

		return new UsernamePasswordAuthenticationToken(name, password, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);

	}

	private boolean emailExist(String email) {
		User user = userRepo.findByEmail(email);
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	// @Override
	public Optional<User> read(String id) {
		return userRepo.findById(id);
	}

	// @Override
	public User update(User document) {
		return userRepo.save(document);
	}

	// @Override
	public void delete(User document) {
		userRepo.delete(document);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findByEmail(username);
		if (user == null) {
			throw new ServiceException("user.not.found");
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				convertSecurityRoleToGrantedAuthorities(user.getSecurityRoles()));
	}

	private static List<GrantedAuthority> convertSecurityRoleToGrantedAuthorities(Collection<SecurityRole> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (SecurityRole role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.name()));
		}
		return authorities;
	}

}
