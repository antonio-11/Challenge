package com.gbm.invest.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.gbm.invest.entity.InitialBalances;
import com.gbm.invest.entity.IssuersData;
import com.gbm.invest.entity.Order;



@Service
public class ComputeOrder {	
	private InitialBalances initialBalance; 
	private Order order;
	
	private int findIndexIssuer(List<IssuersData> isuers) {
		int index = -1; 
		int i;
		for(i=0; i<isuers.size();i++) {			
			if( isuers.get(i).getIssuerName().equalsIgnoreCase(order.getIssuerName()) ) {				
				index = i;
				continue;
			}
		}
		return index;
	}
	
	private double computeCash() {			
		return  order.calculateCash(initialBalance.getCash() );
	}
	
	private void computeTotalShare() {
		int index = findIndexIssuer(initialBalance.getIssuers());		
		int total = order.calculateTotalShare( initialBalance, index  );
		
		//si total es mayor a cero, se aigna el valor como nuevo total de existencias
		//los otros casos ya se consideraron en la clase especializada, ya que es dferente por cada casos
		if ( total > 0) {
			initialBalance.getIssuers().get(index).setTotalShares(total);;			
		}
	}

	public void compute(InitialBalances initialBalances, Order order) {
		this.initialBalance = initialBalances;
		this.order= order;

		initialBalances.setCash( computeCash() );
		computeTotalShare();			
	}

}
