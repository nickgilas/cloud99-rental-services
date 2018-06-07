package com.cloud99.rental.domain;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

public class Membership implements MongoDocument {

    @Id
    private String id;
    private String name;
    private List<SecurityAccess> securityAccess;
    private DateTime createDate;
    private DateTime updateDate;
    private Integer price;

    // private Coupon coupon;
    
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

    public List<SecurityAccess> getSecurityAccess() {
	return securityAccess;
    }

    public void setSecurityAccess(List<SecurityAccess> securityAccess) {
	this.securityAccess = securityAccess;
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

    public Integer getPrice() {
	return price;
    }

    public void setPrice(Integer price) {
	this.price = price;
    }

}
