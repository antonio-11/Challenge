package com.gbm.invest.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbm.invest.common.CommonConstant;
import com.gbm.invest.entity.BussinesError;
import com.gbm.invest.entity.InitialBalances;
import com.gbm.invest.entity.Order;
import com.gbm.invest.service.OrderCache;

@Service
public class ValidUnique implements IValidate{
	
	@Autowired
	private  BussinesError error;
	
	@Autowired
	private OrderCache cache;
	
	@Override
	public boolean validate(InitialBalances initialBalances, Order order) {
		if (!cache.store(order.toString(), order.getTimestamp()*1000)) {
			error.getError().add(CommonConstant.INVALID_DUPLICATED);
			return false;
		}						
		return true;
	}
	

}
