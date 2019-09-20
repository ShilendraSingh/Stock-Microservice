package com.hcl.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.api.entity.UserStock;

public interface UserStockRepository extends JpaRepository<UserStock, Integer>{

}
