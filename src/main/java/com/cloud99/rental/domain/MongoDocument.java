package com.cloud99.rental.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public interface MongoDocument<ID> {

	@Id
	public ID getId();

	default String toJsonString() {
		try {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toString();
	}
}
