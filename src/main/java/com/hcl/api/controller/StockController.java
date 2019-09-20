package com.hcl.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.api.dto.OrderResponseDto;
import com.hcl.api.dto.StockRequestDto;
import com.hcl.api.dto.StockResponseDto;
import com.hcl.api.service.StockService;

@RestController
@RequestMapping("/stock")
// @CrossOrigin(allowedHeaders = {"*","/"}, origins = {"*","/"})
public class StockController {

	@Autowired
	private StockService stockService;

	@GetMapping("/stockUnitPrice/{stockId}")
	public ResponseEntity<OrderResponseDto> getstockPrice(@PathVariable Integer stockId) {

		return new ResponseEntity<>(stockService.getstockPrice(stockId), HttpStatus.OK);

	}
	@PostMapping("/buyStock")
	public ResponseEntity<StockResponseDto> getTotoalStockPrice(@RequestBody StockRequestDto stockRequestDto) {

	
		
		return new ResponseEntity<>(stockService.buyStock(stockRequestDto), HttpStatus.OK);
	
	
	
	}
}