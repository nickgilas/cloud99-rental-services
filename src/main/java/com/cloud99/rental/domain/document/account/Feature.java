package com.cloud99.rental.domain.document.account;

import com.cloud99.rental.domain.document.MongoDocument;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

/**
 * Features are defined in our data store and are dynamically read at runtime.
 */
public class Feature implements MongoDocument<String> {

	@Id
	private String id;

	@NotNull(message = "feature.name.required")
	private String name;
	private String description;


	private LocalDateTime enabledDate;
	private LocalDateTime expireDate;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getEnabledDate() {
		return enabledDate;
	}

	public void setEnabledDate(LocalDateTime enabledDate) {
		this.enabledDate = enabledDate;
	}

	public LocalDateTime getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDateTime expireDate) {
		this.expireDate = expireDate;
	}

	public void setId(String id) {
		this.id = id;
	}

}
