package com.hcl.api.entity;

import java.io.Serializable;

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
public class Stock implements Serializable{
	
	
	private static final long serialVersionUID = 5182926052677816969L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer stockId;
	private String stockName;
	private String stockDescription;
	private Double stockUnitPrice;
	
	

}
