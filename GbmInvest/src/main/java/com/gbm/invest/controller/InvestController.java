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
import com.gbm.invest.entity.BussinesError;
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
	private BussinesError error; 
	
	@PostMapping("v2/invest")
	public ResponseEntity<?> processInvest2(@RequestBody Balances balance) throws Exception{		
		error.setError(new ArrayList<String>());
		
		for (Order order : balance.getOrders()) {
			if ( validateOrder.validate(balance.getInitialBalances(),order) ) {
				computeOrder.compute(balance.getInitialBalances(), order);
			}
		}
				
		return new ResponseEntity<>(createResponse(balance), HttpStatus.OK);
	}
	
	private ResponseBalance createResponse(Balances balance) {
		ResponseBalance response = new ResponseBalance();
		response.getCurrentBalance().setCash(balance.getInitialBalances().getCash());
		response.getCurrentBalance().setIssuers(balance.getInitialBalances().getIssuers());
		response.setBussinessErrors(error.getError());
		return response;
	}

}
