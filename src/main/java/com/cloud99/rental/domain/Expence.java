package com.cloud99.rental.domain;

import org.springframework.data.annotation.Id;

public class Expence implements MongoDocument {

    @Id
    private String id;
    
    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }
}
