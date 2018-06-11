package com.cloud99.rental.domain;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class Person implements MongoDocument<String> {

	static enum Gender {
		MALE, FEMALE
	}

	@Id
	private String id;

	@NotNull(message = "person.name.required")
	private Name name;

	@Email(message = "person.email.required")
	private String email;

	private Gender gender;

	private Integer age;


	private DateTime createDate;
	private DateTime updateDate;

	@Override
	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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
