package com.gbm.invest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gbm.invest.entity.InitialBalances;
import com.gbm.invest.entity.Order;

@Service
public class ComputeOrder {	
	
	public Boolean computeCash(InitialBalances initialBalance ,List<Order> orders) {
		for(Order o :orders) {
			if( o.validate(initialBalance) ) {				
				initialBalance.setCash( o.calculateCash(initialBalance.getCash()) );
			}else {
				System.out.println("INSUFFICIENT_BALANCE");
				//error.add("INSUFFICIENT_BALANCE");
				return false;
			}
		}		
		return true;
	}
	
	public Boolean computeTotalShare(InitialBalances initialBalance ,List<Order> orders) {		
		for(Order o :orders) {	
			if ( o.validate(initialBalance) ) {		
				initialBalance.getIssuers().get(0).setTotalShares( o.calculateTotalShare( initialBalance.getIssuers().get(0).getTotalShares() ) );
			}else {
				System.out.println("INSUFFICIENT_STOCK");
				//error.add("INSUFFICIENT_STOCK");
				return false;
			}				
	    }					
		
		return true;
	}

}
