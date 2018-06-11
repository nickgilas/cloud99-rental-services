package com.cloud99.rental.domain.account;

import com.cloud99.rental.domain.MongoDocument;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account implements MongoDocument<String> {

	public static enum Status {
		CLOSED, ACTIVE, SUPPENDED, PENDING
	}

	@Id
	private String id;
	private String name;
	private Map<String, Collection<Feature>> userFeatureAccess = new HashMap<>();
	private LocalDateTime createDate;
	private LocalDateTime updateDate;

	private List<Subscription> subscriptions;

	private Status status;

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

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public void addUserFeatureAccess(String userName, List<Feature> features) {
		userFeatureAccess.put(userName, features);
	}

	public Map<String, Collection<Feature>> getUserFeatureAccess() {
		return userFeatureAccess;
	}

	public void setUserFeatureAccess(Map<String, Collection<Feature>> userFeatureAccess) {
		this.userFeatureAccess = userFeatureAccess;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
