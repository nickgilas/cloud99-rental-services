package com.cloud99.rental.domain.document;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Property implements MongoDocument {

    static enum Type {
	SINGLE, DUPLEX, TRIPLEX, QUAD
    }

    @Id
    private String id;
    private String name;
    private Address address;
    private Type type;
    private PropertyFinancials propertyFinancials;
    private List<Unit> units;

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

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
    }

    public Type getType() {
	return type;
    }

    public void setType(Type type) {
	this.type = type;
    }

    public PropertyFinancials getPropertyFinancials() {
	return propertyFinancials;
    }

    public void setPropertyFinancials(PropertyFinancials propertyFinancials) {
	this.propertyFinancials = propertyFinancials;
    }

    public List<Unit> getUnits() {
	return units;
    }

    public void setUnits(List<Unit> units) {
	this.units = units;
    }

}
