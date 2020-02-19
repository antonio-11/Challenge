package com.gbm.invest.entity;

import java.util.List;


public class CurrentBalance {
	private double cash;
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
