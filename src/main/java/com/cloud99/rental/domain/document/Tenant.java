package com.cloud99.rental.domain.document;

import org.springframework.data.annotation.Id;

public class Tenant extends Person {

    @Id
    private String id;
    private Name name;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public Name getName() {
	return name;
    }

    public void setName(Name name) {
	this.name = name;
    }

}
