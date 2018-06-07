package com.cloud99.rental.domain;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

public class Account implements MongoDocument {

    static enum Status {
	CLOSED, ACTIVE, SUPPENDED
    }

    @Id
    private String id;
    private String name;
    private DateTime createDate;
    private DateTime updateDate;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    private List<Membership> memberships;
    private Status status;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public DateTime getCreateDate() {
	return createDate;
    }

    public void setCreateDate(DateTime createDate) {
	this.createDate = createDate;
    }

    public DateTime getUpdateDate() {
	return updateDate;
    }

    public void setUpdateDate(DateTime updateDate) {
	this.updateDate = updateDate;
    }

    public List<Membership> getMemberships() {
	return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
	this.memberships = memberships;
    }

    public Status getStatus() {
	return status;
    }

    public void setStatus(Status status) {
	this.status = status;
    }

}
