package com.gbm.invest.entity;

public class Sell extends Order{
	
	private final String operation = "SELL";
	private final String errorMessage = "INSUFFICIENT_STOCK";
	
	@Override
	public String getOperation() {
		return operation;
	}

	@Override
	public int calculateTotalShare(InitialBalances initialBalance, int indexIssuer) {
		// en ventas no hay caso donde el indexIssuer sea -1
		
		//si el total es -1, se vendió todo y se quita de la lista del issuers
		int total = initialBalance.getIssuers().get(indexIssuer).getTotalShares() - super.getTotalShares();	
		if (total == 0) {
			initialBalance.getIssuers().remove(indexIssuer);
			return -1;
		}
		return total;
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
	public boolean validate(InitialBalances initialBalances, Order order) {
		if (!initialBalances.getIssuers().isEmpty() ) {
			for (IssuersData i: initialBalances.getIssuers()) {
				// validamos que hayan en existencia para el issuer en cuestion
				if (i.getIssuerName().contentEquals(super.getIssuerName()) ) {
					return isValidateTotalShare(i.getTotalShares() );
				}
			}			
		} 					
		return false;
	}
	
	@Override
	public String toString () {
		return " IssuerName:"+super.getIssuerName()+" operation:"+operation+
			   " TotalShares:"+super.getTotalShares()+" SharePrice:"+super.getSharePrice();
	}
	
	private Boolean isValidateTotalShare(int issuerTotalShare) {
		if ( issuerTotalShare >= super.getTotalShares()  ? true: false )
			return true;		
		return false;
	}

}
