package com.tesco.spike.restservice;

import com.tesco.spike.dao.ClockResultDao;
import com.tesco.spike.service.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/basket")
@Scope("request")
@Component
public class BasketResource {

    @Autowired
    private ClockResultDao dao;

    @Autowired
    private JsonConverter jsonConverter;

    public BasketResource() {
    }

    public BasketResource(ClockResultDao dao, JsonConverter jsonConverter) {
        this.dao = dao;
        this.jsonConverter = jsonConverter;
    }

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

        return jsonConverter.fromList(baskets);
    }
}
