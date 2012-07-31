package com.tesco.spike.restservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tesco.spike.dao.ClockResultDao;

@Path("/basket")
@Scope("request")
@Component
public class BasketResource {
	
	@Autowired
	ClockResultDao dao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/transaction/{transactionId}")
	public String basketByTransaction(@PathParam("transactionId") String transactionId) {
		return dao.getClockResultByTransactioNo(transactionId);
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/loyaltycard/{loyaltycard}")
	public String basketsForLoyaltyCard(@PathParam("loyaltycard") String loyaltyCard) {
		List<String> baskets = dao.getClockResultByLoyaltyCardNo(loyaltyCard);
		//TODO : return appropriate JSON array
		return baskets.toString();
	}
}
