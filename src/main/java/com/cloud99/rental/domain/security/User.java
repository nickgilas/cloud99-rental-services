package com.cloud99.rental.domain.security;

import com.cloud99.rental.domain.Person;
import com.cloud99.rental.validation.PasswordMatches;

import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.Currency;

@PasswordMatches
public class User extends AbstractSecurityResource {

	@Id
	private String id;

	@NotNull(message = "required.email")
	@Email
	@NotEmpty(message = "required.email")
	private String email;

	@Valid
	private Person person;

	@NotNull(message = "password.required")
	@NotEmpty(message = "password.required")
	private String password;

	@NotNull(message = "password.required")
	@NotEmpty(message = "password.required")
	private String matchingPassword;

	private boolean enabled;

	private Currency currency;

	public User() {
		this.enabled = false;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMatchingPassword(String password) {
		this.matchingPassword = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

}
