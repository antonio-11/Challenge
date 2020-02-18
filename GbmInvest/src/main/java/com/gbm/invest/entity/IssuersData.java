package com.gbm.invest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IssuersData {
	
	@JsonProperty("issuerName")
	private String issuerName;
		
	@JsonProperty("totalShares")
	private int totalShares;
	
	@JsonProperty("sharePrice")
	private double sharePrice;
	
	public IssuersData(String issuerName, int totalShares, double sharePrice) {
		this.issuerName = issuerName;
		this.totalShares = totalShares;
		this.sharePrice = sharePrice;
	};

	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public int getTotalShares() {
		return this.totalShares;
	}

	public void setTotalShares(int totalShares) {
		this.totalShares = totalShares;
	}

	public double getSharePrice() {
		return this.sharePrice;
	}

	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}
	
}
