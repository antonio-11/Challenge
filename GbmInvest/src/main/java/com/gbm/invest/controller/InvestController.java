package com.gbm.invest.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbm.invest.entity.Balances;
import com.gbm.invest.entity.Order;
import com.gbm.invest.entity.ResponseBalance;
import com.gbm.invest.service.ComputeOrder;
import com.gbm.invest.service.ValidateOrder;

@RestController
@RequestMapping(value="/", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
public class InvestController {
	
	//@Autowired
	//private InvestRepository investRepository;	
	
	@Autowired
	private ComputeOrder computeOrder;
	
	@Autowired 
	private ValidateOrder validateOrder;
	
	@Autowired
	private ResponseBalance responceBalance;
	
	@PostMapping("v2/invest")
	public ResponseEntity<?> processInvest2(@RequestBody Balances balance) throws Exception{						
		responceBalance.setBussinessErrors(new ArrayList<String>());			
		
		for (Order order : balance.getOrders()) {
			if ( validateOrder.validate(balance.getInitialBalances(),order) ) {
				computeOrder.compute(balance.getInitialBalances(), order);
			}
		}
		
		responceBalance.setCash(balance.getInitialBalances().getCash());
		responceBalance.setIssuers(balance.getInitialBalances().getIssuers());
				
		return new ResponseEntity<>(responceBalance, HttpStatus.OK);
	}

}
