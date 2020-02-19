package com.gbm.invest.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbm.invest.entity.BussinesError;
import com.gbm.invest.entity.InitialBalances;
import com.gbm.invest.entity.Order;

@Service
public class ValidOrder implements IValidate{
	
	@Autowired
	private  BussinesError error;
	
	@Override
	public boolean validate(InitialBalances initialBalances, Order order) {
		if (order.validate(initialBalances, order) )
			return true;
		error.getError().add(order.getErrorMessage());
		return false;
	}

}
