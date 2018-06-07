package com.cloud99.rental.domain;

import org.springframework.data.annotation.Id;

public class Address implements MongoDocument {

    @Id
    private String id;
    private String address1;
    private String address2;
    private String state;
    private String city;
    private String zipCode;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getAddress1() {
	return address1;
    }

    public void setAddress1(String address1) {
	this.address1 = address1;
    }

    public String getAddress2() {
	return address2;
    }

    public void setAddress2(String address2) {
	this.address2 = address2;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getZipCode() {
	return zipCode;
    }

    public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
    }

}
