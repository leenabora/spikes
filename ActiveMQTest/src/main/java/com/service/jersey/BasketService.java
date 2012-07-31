package com.service.jersey;

import org.springframework.stereotype.Service;

import com.service.Basket;

@Service
public class BasketService {

	public Basket getBasketForTransaction(String transactionId) {
		Basket response = new Basket();
		response.setBranch("testbranch");
		response.setCashier("testcashier");
		response.setReceiptno("test1234");
		response.setTillno("tesdf");
		return response;
		
	}
}
