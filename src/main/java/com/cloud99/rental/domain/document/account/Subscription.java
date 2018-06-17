package com.cloud99.rental.domain.document.account;

import com.cloud99.rental.domain.document.MongoDocument;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

import java.util.List;

/**
 * Defines the first attempt at our pricing model. A subscription bundles up one
 * or more features and prices them as a whole.
 * 
 * All subsections are stored in our data store and loaded at runtime for the
 * users accounts
 */
// TODO - NG - The subscription will need to be enhanced to include
// a FeaturePolicy new object which will indicate special restrictions. For
// example,
// the Storage feature will need a FeaturePolicy that defines the amount of
// storage that is allowed for an account.
public class Subscription implements MongoDocument<String> {

	@Id
	private String id;

	@NotNull(message = "subscription.name.required")
	private String name;

	@NotNull(message = "subscription.price.required")
	private Integer price;

	private List<Feature> featureSet;

	private DateTime createDate;
	private DateTime updateDate;

	public List<Feature> getFeatureSet() {
		return featureSet;
	}

	public void setFeatureSet(List<Feature> featureSet) {
		this.featureSet = featureSet;
	}

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
