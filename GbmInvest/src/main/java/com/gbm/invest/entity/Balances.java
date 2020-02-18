package com.gbm.invest.entity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonProperty;

@Repository
public class Balances {
	
	@JsonProperty("initialBalances")
	private InitialBalances initialBalances;
	
	@JsonProperty("orders")
	private List<Order> orders;
	
	public InitialBalances getInitialBalances() {
		return initialBalances;
	}

	public void setInitialBalances(InitialBalances initialBalances) {
		this.initialBalances = initialBalances;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
