package com.gbm.invest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IssuersData {
	
	@JsonProperty("IssuerName")
	private String IssuerName;
		
	@JsonProperty("TotalShares")
	private int TotalShares;
	
	@JsonProperty("SharePrice")
	private double SharePrice;

	public String getIssuerName() {
		return IssuerName;
	}

	public void setIssuerName(String issuerName) {
		IssuerName = issuerName;
	}

	public int getTotalShares() {
		return TotalShares;
	}

	public void setTotalShares(int totalShares) {
		TotalShares = totalShares;
	}

	public double getSharePrice() {
		return SharePrice;
	}

	public void setSharePrice(double sharePrice) {
		SharePrice = sharePrice;
	}
	
}
