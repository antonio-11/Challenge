package com.gbm.invest.service;

import org.springframework.stereotype.Service;
import com.gbm.invest.entity.InitialBalances;
import com.gbm.invest.entity.Order;

@Service
public class ComputeOrder {	
	private InitialBalances initialBalance; 
	private Order order;
	
	private void computeCash() {
		initialBalance.setCash( order.calculateCash(initialBalance.getCash()) );
	}
	
	private void computeTotalShare() {		
		initialBalance.getIssuers().get(0).setTotalShares( order.calculateTotalShare( initialBalance.getIssuers().get(0).getTotalShares() ) );
	}

	public void compute(InitialBalances initialBalances, Order order) {
		this.initialBalance = initialBalances;
		this.order= order;

		computeCash();
		computeTotalShare();			
	}

}
