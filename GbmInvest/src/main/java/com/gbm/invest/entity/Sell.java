package com.gbm.invest.entity;

public class Sell extends Order{
	
	private final String operation = "SELL";
	private final String errorMessage = "INSUFFICIENT_STOCK";
	
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
	public String getErrorMessage() {
		return errorMessage;
	}
	
	@Override
	public Boolean validate(InitialBalances initialBalances) {
		if (!initialBalances.getIssuers().isEmpty() ) {
			for (IssuersData i: initialBalances.getIssuers()) {
				if (i.getIssuerName().contentEquals(super.getIssuerName()) ) {
					return isValidateTotalShare(i.getTotalShares() );
				}
			}			
		} 					
		return false;
	}
	
	private Boolean isValidateTotalShare(int issuerTotalShare) {
		if ( issuerTotalShare >= super.getTotalShares()  ? true: false )
			return true;		
		return false;
	}

}
