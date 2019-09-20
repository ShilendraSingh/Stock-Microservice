package com.hcl.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.api.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

}
