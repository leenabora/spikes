package com.tesco.spike.restservice;

import com.tesco.spike.dao.ClockResultDao;
import com.tesco.spike.service.JsonConverter;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasketResourceTest {

    @Mock
    private ClockResultDao dao;
    @Mock
    private JsonConverter jsonConverter;


    private BasketResource basketResource;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        basketResource = new BasketResource(dao, jsonConverter);
    }

    @Test
    public void shouldFetchBasketResultBasedOnLoyaltyCardNo() {
        String cardNo = "cardNo";

        List<String> list = mock(ArrayList.class);
        when(dao.getClockResultByLoyaltyCardNo(cardNo)).thenReturn(list);

        String basketResult = "{result:'basket Result''}";
        when(jsonConverter.fromList(list)).thenReturn(basketResult);

        String basket = basketResource.basketsForLoyaltyCard(cardNo);

        assertThat(basket, Is.is(basketResult));
    }

    @Test
    public void shouldFetchBasketResultBasedOnTransactionNo() {
        String transactionNo = "transactionNo";

        String basketResult = "{result:'basket Result''}";
        when(dao.getClockResultByTransactioNo(transactionNo)).thenReturn(basketResult);


        String basket = basketResource.basketByTransaction(transactionNo);

        assertThat(basket, Is.is(basketResult));
    }
}
