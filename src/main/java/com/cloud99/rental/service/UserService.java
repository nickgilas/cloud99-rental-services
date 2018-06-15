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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements RentalService, ClientDetailsService, AuthenticationProvider {

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
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String password = authentication.getCredentials().toString();

		Optional<User> optinalUser = read(authentication.getName());
		if (optinalUser.isPresent()) {
			User user = optinalUser.get();
			if (BCrypt.checkpw(password, user.getPassword())) {
				return new UsernamePasswordAuthenticationToken(user, password,
						convertSecurityRoleToGrantedAuthorities(user.getSecurityRoles()));
			}
		}

		return null;

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

	private static List<GrantedAuthority> convertSecurityRoleToGrantedAuthorities(Collection<SecurityRole> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (SecurityRole role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.name()));
		}
		return authorities;
	}

	private static String convertSecurityRoleToString(Collection<SecurityRole> roles) {
		StringBuilder list = new StringBuilder();
		roles.stream().forEach(role -> {
			list.append(role + ",");
		});
		return list.toString().substring(0, list.length() - 1);

	}
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		
		User user = userRepo.findByEmail(clientId);
		if (user == null) {
			throw new ServiceException("user.not.found");
		}
		
		return new BaseClientDetails(user.getEmail(), "", "", "", convertSecurityRoleToString(user.getSecurityRoles()));

	}

}
