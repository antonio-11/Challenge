package com.gbm.invest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbm.invest.entity.Balances;
import com.gbm.invest.service.ComputeOrder;

@RestController
@RequestMapping(value="/", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
public class InvestController {
	
	//@Autowired
	//private InvestRepository investRepository;	
	
	@Autowired
	private ComputeOrder computeOrder;
	
	@PostMapping("v2/invest")
	public ResponseEntity<?> processInvest2(@RequestBody Balances balance) throws Exception{						
				
		if ( computeOrder.computeCash( balance.getInitialBalances() ,balance.getOrders() ) )		
			computeOrder.computeTotalShare( balance.getInitialBalances() ,balance.getOrders() );
		
		//shows cash and total shares
		System.out.println( balance.getInitialBalances().getCash());
		System.out.println( balance.getInitialBalances().getIssuers().get(0).getTotalShares() );
		
		return new ResponseEntity<>("hola", HttpStatus.OK);
	}

}
