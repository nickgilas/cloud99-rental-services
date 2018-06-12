package com.cloud99.rental.service;

import com.cloud99.rental.domain.security.User;
import com.cloud99.rental.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
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
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements RentalService, UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private MessageSource messages;

	@Autowired
	private JavaMailSender mailSender;

	// http://www.baeldung.com/registration-verify-user-by-email

	@Transactional
//	@Override
	public User create(User document, HttpServletRequest request) {

		if (emailExist(document.getPerson().getEmail())) {
			throw new ServiceException("email.exists");
		}
		// encode password
		document.setPassword(BCrypt.hashpw(document.getPassword(), BCrypt.gensalt()));

		String appUrl = request.getContextPath();
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(document, request.getLocale(), appUrl));
		
		return userRepo.save(document);

		// TODO - NG - decrypt password - BCrypt.checkpw
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

		// TODO - incorporate Roles/Authorieies
		// getAuthorities(user.getRoles);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
	}

	private static List<GrantedAuthority> getAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
