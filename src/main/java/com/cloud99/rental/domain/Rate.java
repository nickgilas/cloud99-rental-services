package com.cloud99.rental.domain;

import org.springframework.data.annotation.Id;

public class Rate implements MongoDocument {

    @Id
    private String id;    
    private Double amount;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }
    
    public Double getAmount() {
	return amount;
    }

    public void setAmount(Double amount) {
	this.amount = amount;
    }

}
