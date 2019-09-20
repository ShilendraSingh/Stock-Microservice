package com.hcl.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class UserStock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userStockId;
	private Integer stockId;
	private Integer userId;
	private Integer quantity;
	private Double stockPrice;
	
	

}
