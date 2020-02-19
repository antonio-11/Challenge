package com.gbm.invest.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class OrderCache {
	
	private HashMap<String, Long> cache;
	
	public OrderCache() {
		cache = new HashMap<>();
	}

	public HashMap<String, Long> getCache() {
		return cache;
	}

	public void setCache(HashMap<String, Long> cache) {
		this.cache = cache;
	}

	public boolean store(String orderKey, Long timeStamp) {
		//previous order exists?		
		if ( cache.containsKey(orderKey) ) {	
			if( !isValidTimeStamp(orderKey, timeStamp)) {
				return false;
			}
		}			
		cache.put(orderKey, timeStamp);
		return true;
	}
	
	public boolean isValidTimeStamp(String orderKey, Long timeStamp) {	
		long dif = Math.abs(cache.get(orderKey) - timeStamp);
		int day = (int)dif/(1000*60*60*24);
		int hour =(int)dif/(1000*60*60); 
		int min =(int)dif/(1000*60); 	
		return (day==0 && hour==0 && min < 5) ? false : true;
	}
}
