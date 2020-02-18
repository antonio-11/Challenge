package com.gbm.invest.entity;


public class Buy extends Order {
	
	private final String operation = "BUY";
	private final String errorMessage = "INSUFFICIENT_BALANCE";
	
	@Override
	public String getOperation() {
		return operation;
	}

	@Override
	public int calculateTotalShare(InitialBalances initialbalances, int indexIssuer) {
		// si el indexIssuer es -1, entonces se crea con los datos de la orden
		if (indexIssuer < 0) {
			initialbalances.getIssuers().add(new IssuersData(super.getIssuerName(),
					                                        super.getTotalShares(),
					                                        super.getSharePrice()));
			return -1;
		}
		//si existe, entonces se obtienen las existencias y se suman las compras de la orden 		
		return initialbalances.getIssuers().get(indexIssuer).getTotalShares() + super.getTotalShares();
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
	public boolean validate(InitialBalances initialBalance, Order order) {		
		return isValidateBalance(initialBalance.getCash()); 
	}
	
	@Override
	public String toString () {
		return " IssuerName:"+super.getIssuerName()+" operation:"+operation+
			   " TotalShares:"+super.getTotalShares()+" SharePrice:"+super.getSharePrice();
	}
	
	private Boolean isValidateBalance(double issuerCash) {
		if( issuerCash >= super.getTotalShares() * super.getSharePrice() ? true: false)
			return true;		
		return false; 
	}

}
