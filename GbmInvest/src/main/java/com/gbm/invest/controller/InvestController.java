package com.gbm.invest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbm.invest.entity.Balances;
import com.gbm.invest.entity.BussinesError;
import com.gbm.invest.entity.Order;
import com.gbm.invest.entity.ResponseBalance;
import com.gbm.invest.service.ComputeOrder;
import com.gbm.invest.service.ValidateBalance;
import com.gbm.invest.service.ValidateOrder;

@RestController
@CrossOrigin
@RequestMapping(value="/v2")
public class InvestController {
	
	//@Autowired
	//private InvestRepository investRepository;	
	
	@Autowired
	private ComputeOrder computeOrder;
	
	@Autowired 
	private ValidateOrder validateOrder; 
	
	@Autowired 
	private ValidateBalance validateBalance;
	
	@Autowired
	private BussinesError error;
	
	@GetMapping(value="/status")
	String checkStatus() {
		return "ok";
	}
	
	@PostMapping(value="/invest", produces=MediaType.APPLICATION_JSON_VALUE, 
			                      consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> processInvest2(@RequestBody Balances balance) {
		error.getError().clear();
		
		if(validateBalance.validate(balance))
		for (Order order : balance.getOrders()) {
			if ( validateOrder.validate(balance.getInitialBalances(),order) ) {
				computeOrder.compute(balance.getInitialBalances(), order);
			}
		}
	
		return new ResponseEntity<>(new ResponseBalance(balance, error), HttpStatus.OK);
	}

}
