package com.gbm.invest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbm.invest.entity.InitialBalances;
import com.gbm.invest.entity.Order;
import com.gbm.invest.validation.IValidate;
import com.gbm.invest.validation.ValidDate;
import com.gbm.invest.validation.ValidOrder;
import com.gbm.invest.validation.ValidUnique;

@Service
public class ValidateOrder {			
	@Autowired
	private ValidDate validDate;
	
	@Autowired
	private ValidOrder validOrder;
	
	@Autowired
	private ValidUnique validUnique;
	
	private List<IValidate> list;
	
	public boolean validate(InitialBalances initialBalances, Order order) {	
		for (IValidate i : list ) {
			if (!i.validate(initialBalances, order)) { 
				return false;
			}
		}
		return true;
	}
	
	public void initValidation() {
		 list = new ArrayList<IValidate>();
		 list.add(validDate);
		 list.add(validOrder);
		 list.add(validUnique);
	}
}
