package com.gbm.invest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbm.invest.common.CommonConstant;
import com.gbm.invest.entity.Balances;
import com.gbm.invest.entity.BussinesError;
import com.gbm.invest.entity.IssuersData;

@Service
public class ValidateBalance {
	
	@Autowired
	private  BussinesError error;
	
	public boolean validate(Balances balance) {
		
		if(balance.getInitialBalances().getCash() > 0) {
			for (IssuersData i:balance.getInitialBalances().getIssuers()) {
				if (i.getSharePrice() < 0 || i.getTotalShares() < 0 ) {
					error.getError().add(CommonConstant.INVALID_POSITIVE);
					return false;
					}
			}
		}else {
			error.getError().add(CommonConstant.INVALID_POSITIVE);
			return false;
			}
		
		return true;
	}
}
