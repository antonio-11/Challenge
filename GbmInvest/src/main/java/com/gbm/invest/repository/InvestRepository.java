package com.gbm.invest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gbm.invest.entity.Invest;

public interface InvestRepository extends JpaRepository<Invest, Long>{

}
