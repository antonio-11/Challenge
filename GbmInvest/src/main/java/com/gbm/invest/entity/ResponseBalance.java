package com.gbm.invest.entity;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ResponseBalance {
	
	private CurrentBalance currentBalance = new CurrentBalance();
	
	private List<String> bussinessErrors;			
	
	public CurrentBalance getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(CurrentBalance currentBalance) {
		this.currentBalance = currentBalance;
	}
	public List<String> getBussinessErrors() {
		return bussinessErrors;
	}
	public void setBussinessErrors(List<String> bussinessErrors) {
		this.bussinessErrors = bussinessErrors;
	}
}
