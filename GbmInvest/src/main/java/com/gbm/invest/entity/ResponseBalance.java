package com.gbm.invest.entity;

import java.util.ArrayList;
import java.util.List;

public class ResponseBalance {		
	
	private CurrentBalance currentBalance;
	
	private List<String> bussinessErrors;	
	
	public ResponseBalance(Balances balance, BussinesError error) {
		currentBalance = new CurrentBalance();
		bussinessErrors = new ArrayList<String>();
		currentBalance.setCash(balance.getInitialBalances().getCash());
		currentBalance.setIssuers(balance.getInitialBalances().getIssuers());
		bussinessErrors.addAll(error.getError());
		//error.getError().clear();
	}
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
