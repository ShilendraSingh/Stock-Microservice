package com.hcl.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockRequestDto {

	private Integer userId;
	private Integer stockId;
	private Integer quantity;
	
}
