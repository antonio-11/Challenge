package com.gbm.invest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbm.invest.validation.IValidate;
import com.gbm.invest.validation.ValidDate;
import com.gbm.invest.validation.ValidOrder;
import com.gbm.invest.validation.ValidPositive;
import com.gbm.invest.validation.ValidUnique;

@Service
public class ValidationList {
	
	@Autowired
	private ValidDate validDate;
	
	@Autowired
	private ValidOrder validOrder;
	
	@Autowired
	private ValidUnique validUnique;
	
	@Autowired
	private ValidPositive validPositive;
	
	private List<IValidate> list;
	
	public ValidationList() {
		list= new ArrayList<>();
	}
	public void initValidationList() {		 
		 list.add(validDate);
		 list.add(validOrder);
		 list.add(validUnique);
		 list.add(validPositive);
	}

	public List<IValidate> getList() {
		if( list.isEmpty() ) 
			initValidationList();
		return list;
	}

	public void setList(List<IValidate> list) {
		this.list = list;
	}	

}
