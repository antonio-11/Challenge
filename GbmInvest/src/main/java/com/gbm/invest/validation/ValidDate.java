package com.gbm.invest.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbm.invest.common.CommonConstant;
import com.gbm.invest.entity.BussinesError;
import com.gbm.invest.entity.InitialBalances;
import com.gbm.invest.entity.Order;

@Service
public class ValidDate implements IValidate{
	
	@Autowired
	private  BussinesError error;

	@Override
	public boolean validate(InitialBalances initialBalances, Order order) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(CommonConstant.FORMAT_HOUR_DATE);
			sdf.setTimeZone(TimeZone.getTimeZone("GMT-06:00"));
			Date orderDate = sdf.parse( order.getTimeStringTimeStamp() );
			Date openMarket = sdf.parse(CommonConstant.FORMAT_OPEN_MARKET);
			Date closeMarket = sdf.parse(CommonConstant.FORMAT_CLOSE_MARKET);
			if (orderDate.after(openMarket) && orderDate.before(closeMarket))
				return true;		
			error.getError().add(CommonConstant.INVALID_CLOSE);
		}catch(ParseException pe) {
			error.getError().add(CommonConstant.INVALID_CLOSE);
		}
		return false;
	}

}
