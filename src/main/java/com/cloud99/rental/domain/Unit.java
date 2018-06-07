package com.cloud99.rental.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Unit implements MongoDocument {

    @Id
    private String id;
    private String name;
    private String description;
    private Integer number;
    private Double squareFeet;
    private Rate rentalRate;
    private Integer beds;
    private Integer baths;
    private Integer depositAmount;
    private List<Lease> leases;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }
    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Integer getNumber() {
	return number;
    }

    public void setNumber(Integer number) {
	this.number = number;
    }

    public Double getSquareFeet() {
	return squareFeet;
    }

    public void setSquareFeet(Double squareFeet) {
	this.squareFeet = squareFeet;
    }

    public Rate getRentalRate() {
	return rentalRate;
    }

    public void setRentalRate(Rate rentalRate) {
	this.rentalRate = rentalRate;
    }

    public Integer getBeds() {
	return beds;
    }

    public void setBeds(Integer beds) {
	this.beds = beds;
    }

    public Integer getBaths() {
	return baths;
    }

    public void setBaths(Integer baths) {
	this.baths = baths;
    }

    public Integer getDepositAmount() {
	return depositAmount;
    }

    public void setDepositAmount(Integer depositAmount) {
	this.depositAmount = depositAmount;
    }

    public List<Lease> getLeases() {
	return leases;
    }

    public void setLeases(List<Lease> leases) {
	this.leases = leases;
    }

    // private List<Amentities> amentities;

}
