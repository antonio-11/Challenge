package com.gbm.invest.entity;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BussinesError {
	private List<String> error;

	public List<String> getError() {
		return error;
	}

	public void setError(List<String> error) {
		this.error = error;
	}
	
	
	
}
