package com.gbm.invest.entity;

public interface ICalculate {
	public int calculateTotalShare(InitialBalances initialBalance,int indexIusser);
	public double calculateCash(double issuerCash);	 
}
