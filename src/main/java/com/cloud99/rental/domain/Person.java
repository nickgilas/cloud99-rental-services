package com.cloud99.rental.domain;

import org.joda.time.DateTime;

public abstract class Person implements MongoDocument {

    static enum Gender {
	MALE, FEMALE
    }

    private Name name;
    private Gender gender;
    private Integer age;
    private DateTime createDate;
    private DateTime updateDate;

    public Name getName() {
	return name;
    }

    public void setName(Name name) {
	this.name = name;
    }

    public Gender getGender() {
	return gender;
    }

    public void setGender(Gender gender) {
	this.gender = gender;
    }

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
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

}
