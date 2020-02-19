package com.gbm.invest.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.gbm.invest.validation.IValidate;

@JsonTypeInfo(property = "operation", use = Id.NAME, include = As.PROPERTY)
@JsonSubTypes({
	@JsonSubTypes.Type(value = Buy.class, name="BUY"),
	@JsonSubTypes.Type(value = Sell.class, name="SELL")
})
public abstract class Order implements ICalculate, IValidate{
	
	@JsonProperty("timestamp")
	private long timestamp;
	
	@JsonProperty("operation")
	private String operation;
	
	@JsonProperty("IssuerName")
	private String IssuerName;
	
	@JsonProperty("TotalShares")
	private int TotalShares;
	
	@JsonProperty("SharePrice")
	private double SharePrice;
	
	@JsonIgnore
	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getIssuerName() {
		return IssuerName;
	}

	public void setIssuerName(String IssuerName) {
		this.IssuerName = IssuerName;
	}

	public int getTotalShares() {
		return TotalShares;
	}

	public void setTotalShares(int TotalShares) {
		this.TotalShares = TotalShares;
	}

	public double getSharePrice() {
		return SharePrice;
	}

	public void setSharePrice(double SharePrice) {
		this.SharePrice = SharePrice;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public Date getCustomDateTimestamp() {		
		return new Date(timestamp * 1000);
	}
	
	public String getTimeStringTimeStamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(getCustomDateTimestamp()) ;
	}	
	
	public String toString () {
		return toString();
	}
}
