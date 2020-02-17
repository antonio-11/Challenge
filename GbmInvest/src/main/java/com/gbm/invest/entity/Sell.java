package com.gbm.invest.entity;

public class Sell extends Order{
	
private final String operation = "SELL";
	
	@Override
	public String getOperation() {
		return operation;
	}

	@Override
	public int calculateTotalShare(int iusserTotalShare) {
		return iusserTotalShare - super.getTotalShares();
	}

	@Override
	public double calculateCash(double issuerCash) {
		return  issuerCash + (super.getTotalShares() * super.getSharePrice() );
	}

	@Override
	public Boolean validate(InitialBalances initialBalances) {
		return initialBalances.getIssuers().get(0).getTotalShares() > super.getTotalShares()  ? true: false; 
	}

}
