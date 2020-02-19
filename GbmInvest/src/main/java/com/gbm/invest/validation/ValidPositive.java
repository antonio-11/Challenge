package com.gbm.invest.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbm.invest.common.CommonConstant;
import com.gbm.invest.entity.BussinesError;
import com.gbm.invest.entity.InitialBalances;
import com.gbm.invest.entity.Order;

@Service
public class ValidPositive implements IValidate{
	
	@Autowired
	private  BussinesError error;

	@Override
	public boolean validate(InitialBalances initialBalance, Order order) {
		if ( order.getSharePrice()>0 && order.getTotalShares() > 0) {
			return true;	
		}
		error.getError().add(CommonConstant.INVALID_POSITIVE);
		return false;
	}
	
	

}
