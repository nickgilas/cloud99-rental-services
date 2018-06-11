package com.cloud99.rental.service;

import com.cloud99.rental.domain.security.User;
import com.cloud99.rental.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements RentalService, CrudService<User, UserRepo> {

	@Autowired
	private UserRepo userRepo;

	@Override
	public User create(User document) {
		// encode password
		document.setPassword(BCrypt.hashpw(document.getPassword(), BCrypt.gensalt()));
		return userRepo.save(document);

		// TODO - NG - decrypt password - BCrypt.checkpw
	}

	@Override
	public Optional<User> read(String id) {
		return userRepo.findById(id);
	}

	@Override
	public User update(User document) {
		return userRepo.save(document);
	}

	@Override
	public void delete(User document) {
		userRepo.delete(document);
	}

}
