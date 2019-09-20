package com.hcl.api.service;

import com.hcl.api.dto.OrderResponseDto;
import com.hcl.api.dto.StockRequestDto;
import com.hcl.api.dto.StockResponseDto;

public interface StockService {
	
	public OrderResponseDto getstockPrice(Integer stockId);
	
	public StockResponseDto buyStock(StockRequestDto stockRequestDto);

}
