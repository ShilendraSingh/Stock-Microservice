package com.hcl.api.service;


import java.util.Arrays;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.api.dto.GlobalQuoteDto;
import com.hcl.api.dto.OrderResponseDto;
import com.hcl.api.dto.QuoteResponsedto;
import com.hcl.api.dto.StockRequestDto;
import com.hcl.api.dto.StockResponseDto;
import com.hcl.api.entity.Stock;
import com.hcl.api.entity.User;
import com.hcl.api.entity.UserStock;
import com.hcl.api.repository.StockRepository;
import com.hcl.api.repository.UserRepository;
import com.hcl.api.repository.UserStockRepository;

@Service
public class StockServiceImpl implements StockService {

	

	@Autowired
	StockRepository stockRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	UserStockRepository userStockRepository;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public OrderResponseDto getstockPrice(Integer stockId) {
		
		OrderResponseDto orderResponseDto=new OrderResponseDto();
		Optional<Stock> optionalStock = stockRepository.findById(stockId);

		
		

		if (optionalStock.isPresent()) {
			Stock stock = optionalStock.get();
			
			  
			ResponseEntity<GlobalQuoteDto> latest=	latestStockPrice(stock.getStockName())	;	

			orderResponseDto.setActualStockPrice(stock.getStockUnitPrice());
			orderResponseDto.setLatestPrice(latest.getBody().getGlobalQuote().getPrice());
			orderResponseDto.setOrderId(stock.getStockId());
		}

		return orderResponseDto;
	}
	
	public ResponseEntity<GlobalQuoteDto> latestStockPrice(String stockName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		 headers.add("contentType","application/json");
		HttpEntity<String> entity = new HttpEntity<>(headers);

		return new ResponseEntity<GlobalQuoteDto>(
				restTemplate.exchange("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + stockName
						+ "&apikey=CQA8OG03A7GVM5ZU", HttpMethod.GET, entity, GlobalQuoteDto.class).getBody(),
				HttpStatus.OK);


}

	@Override
	public StockResponseDto buyStock(StockRequestDto stockRequestDto) {
		
		StockResponseDto stockResponseDto=new StockResponseDto();

		User user =userRepository.findById(stockRequestDto.getUserId()).get();
		
		Stock stock=stockRepository.findById(stockRequestDto.getStockId()).get();
		UserStock userStock=new UserStock();
		String url = "http://localhost:9911/quote/quote/totalStockPrice/" + stockRequestDto.getStockId();
		
		QuoteResponsedto  quoteResponsedto=restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<QuoteResponsedto>() {}).getBody();
	
		userStock.setUserId(user.getUserId());
		userStock.setStockId(stock.getStockId());
		userStock.setQuantity(stockRequestDto.getQuantity());
		Double priceStock=quoteResponsedto.getTotalStockAmount()*stockRequestDto.getQuantity()+quoteResponsedto.getCharges();
		userStock.setStockPrice(priceStock);
		userStockRepository.save(userStock);
		
		stockResponseDto.setMessage("Stock bought succesfully");
		stockResponseDto.setStatusCode(201);
		return stockResponseDto;
	}
}