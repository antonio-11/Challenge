package com.gbm.invest.entity;

import java.util.List;

public class ResponseBalance {
	private double cash;
	private List<IssuersData> issuers;
	private List<String> bussinessErrors;
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
	public List<String> getBussinessErrors() {
		return bussinessErrors;
	}
	public void setBussinessErrors(List<String> bussinessErrors) {
		this.bussinessErrors = bussinessErrors;
	}
	
	
}
