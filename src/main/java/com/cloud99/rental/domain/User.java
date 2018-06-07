package com.cloud99.rental.domain;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

public class User extends Person {

    @Id
    private String id;
    
    @NotNull(message = "required.email")
    private String email;
    private String password;
    private List<SecurityAccess> securityAccess;

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

    public List<SecurityAccess> getSecurityAccess() {
	return securityAccess;
    }

    public void setSecurityAccess(List<SecurityAccess> securityAccess) {
	this.securityAccess = securityAccess;
    }

}
