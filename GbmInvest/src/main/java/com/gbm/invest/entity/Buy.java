package com.gbm.invest.entity;


public class Buy extends Order {
	
	private final String operation = "BUY";
	private final String errorMessage = "INSUFFICIENT_BALANCE";
	
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
	public String getErrorMessage() {
		return errorMessage;
	}
	
	@Override
	public Boolean validate(InitialBalances initialBalance) {		
		return isValidateBalance(initialBalance.getCash()); 
	}
	
	private Boolean isValidateBalance(double issuerCash) {
		if( issuerCash >= super.getTotalShares() * super.getSharePrice() ? true: false)
			return true;		
		return false; 
	}

}
