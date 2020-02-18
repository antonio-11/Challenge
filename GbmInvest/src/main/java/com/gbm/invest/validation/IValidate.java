package com.gbm.invest.validation;

import com.gbm.invest.entity.InitialBalances;
import com.gbm.invest.entity.Order;

public interface IValidate {
	public boolean validate(InitialBalances  initialBalances, Order order);
}