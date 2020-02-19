package com.gbm.invest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbm.invest.entity.InitialBalances;
import com.gbm.invest.entity.Order;
import com.gbm.invest.validation.IValidate;

@Service
public class ValidateOrder {			
	
	@Autowired
	private ValidationList list;
	
	public boolean validate(InitialBalances initialBalances, Order order) {	
		for (IValidate i : list.getList() ) {
			if (!i.validate(initialBalances, order)) { 
				return false;
			}
		}
		return true;
	}	
}
