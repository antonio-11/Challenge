package com.gbm.invest.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class OrderCache {
	
	private HashMap<String, Long> cache = new HashMap<>();

	public HashMap<String, Long> getCache() {
		return cache;
	}

	public void setCache(HashMap<String, Long> cache) {
		this.cache = cache;
	}

	public boolean store(String orderKey, Long timeStamp) {
		//previous order exists?
		System.out.println("Revisamos si el objeto orderKey exuste:"+orderKey);
		if ( cache.containsKey(orderKey) ) {	
			System.out.println("Hay una orden similar, revisamos el timestamp");
			if( !isValidTimeStamp(orderKey, timeStamp)) {
				System.out.println("no es valido el timestamp:"+timeStamp);
				return false;
			}
		}	
		System.out.println("Si llegaste aqui, eres un tiemstap valido, te guardo en cahe: "+timeStamp);
		System.out.println("con la llave: "+orderKey);
		cache.put(orderKey, timeStamp);
		return true;
	}
	
	public boolean isValidTimeStamp(String orderKey, Long timeStamp) {	
		System.out.println("Revisamos el timestap: "+timeStamp);
		System.out.println("contra el que hay en la base: "+cache.get(orderKey));
		long dif = Math.abs(cache.get(orderKey) - timeStamp);
		int day = (int)dif/(1000*60*60*24);
		int hour =(int)dif/(1000*60*60); 
		int min =(int)dif/(1000*60); 
		System.out.println("dif:"+dif+" day:"+day+" hour:"+hour+" min:"+min);		
		return (day==0 && hour==0 && min < 5) ? false : true;
	}
}
