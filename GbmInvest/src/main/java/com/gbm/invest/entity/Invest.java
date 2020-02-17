package com.gbm.invest.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="investment")
public class Invest {
	
	@Id @GeneratedValue
	private Long investId;	
	private long cash;
	private String issuerName;
	private int totalShares;
	private long sharePrice;
	private Date timeStamp;
	private String operationType;
		
	public Invest() {}

	public Long getInvestId() {
		return investId;
	}

	public void setInvestId(Long investId) {
		this.investId = investId;
	}

	public long getCash() {
		return cash;
	}

	public void setCash(long cash) {
		this.cash = cash;
	}

	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public int getTotalShares() {
		return totalShares;
	}

	public void setTotalShares(int totalShares) {
		this.totalShares = totalShares;
	}

	public long getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(long sharePrice) {
		this.sharePrice = sharePrice;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}	
}
