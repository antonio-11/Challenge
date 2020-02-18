package com.gbm.invest.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class InitialBalances {
	
	@JsonProperty("cash")
	private double cash;
	
	@JsonProperty("issuers")
	private List<IssuersData> issuers;

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public List<IssuersData> getIssuers() {
		return issuers;
	}

	public void setIssuers(List<IssuersData> issuers) {
		this.issuers = issuers;
	}	
	
}
