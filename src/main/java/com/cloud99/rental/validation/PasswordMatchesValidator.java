package com.cloud99.rental.validation;

import com.cloud99.rental.domain.security.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, User> {

	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {
		return user.getPerson().getEmail().equals(user.getMatchingPassword());
	}

}
