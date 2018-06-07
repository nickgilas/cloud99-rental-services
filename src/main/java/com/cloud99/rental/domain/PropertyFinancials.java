package com.cloud99.rental.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

public class PropertyFinancials implements MongoDocument {

    @Id
    private String id;
    private Long income;
    private List<Expence> expences;
    private Integer annualTaxes;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }
    
    public Long getIncome() {
	return income;
    }

    public void setIncome(Long income) {
	this.income = income;
    }

    public List<Expence> getExpences() {
	return expences;
    }

    public void setExpences(List<Expence> expences) {
	this.expences = expences;
    }

    public Integer getAnnualTaxes() {
	return annualTaxes;
    }

    public void setAnnualTaxes(Integer annualTaxes) {
	this.annualTaxes = annualTaxes;
    }

}
