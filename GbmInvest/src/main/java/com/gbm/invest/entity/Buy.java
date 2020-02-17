package com.gbm.invest.entity;

public class Buy extends Order {
	
	private final String operation = "BUY";
	
	@Override
	public String getOperation() {
		return operation;
	}

	@Override
	public int calculateTotalShare(int iusserTotalShare) {
		return iusserTotalShare + super.getTotalShares();
	}

	@Override
	public double calculateCash(double issuerCash) {		
		return  issuerCash - (super.getTotalShares() * super.getSharePrice() );
	}

	@Override
	public Boolean validate(InitialBalances initialBalance) {
		return initialBalance.getCash() > super.getTotalShares() * super.getSharePrice()  ? true: false; 
	}

}
