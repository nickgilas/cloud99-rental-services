package com.cloud99.rental.domain;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

public class SecurityAccess implements MongoDocument {

    static enum Access {
	READ, WRITE, NONE
    }

    @Id
    private String id;
    private String name;
    private String feature;
    private DateTime createDate;
    private Access access;

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

    public String getFeature() {
	return feature;
    }

    public void setFeature(String feature) {
	this.feature = feature;
    }

    public DateTime getCreateDate() {
	return createDate;
    }

    public void setCreateDate(DateTime createDate) {
	this.createDate = createDate;
    }

    public Access getAccess() {
	return access;
    }

    public void setAccess(Access access) {
	this.access = access;
    }

}
