package com.cloud99.rental.domain.security;

import com.cloud99.rental.domain.Person;

import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.Currency;

public class User extends AbstractSecurityResource {

	@Id
	private String id;

	// @NotNull(message = "user.name.required")

	@NotNull(message = "required.email")
	private String email;

	@Valid
	private Person person;

	private String password;
	private boolean enabled;
	private Currency currency;

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

}
