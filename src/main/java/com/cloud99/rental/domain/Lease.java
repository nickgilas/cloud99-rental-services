package com.cloud99.rental.domain;

import com.cloud99.rental.domain.security.AbstractSecurityResource;

import org.springframework.data.annotation.Id;

public class Lease extends AbstractSecurityResource {

    @Id
    private String id;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

}
