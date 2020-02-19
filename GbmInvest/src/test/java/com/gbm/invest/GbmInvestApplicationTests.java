package com.gbm.invest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gbm.invest.controller.InvestController;

@SpringBootTest
class GbmInvestApplicationTests {

	@Autowired 
	private InvestController controller;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
