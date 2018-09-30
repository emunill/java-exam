package com.privalia.business.unit.test;

import com.privalia.model.Broker;
import com.privalia.model.IBroker;
import com.privalia.model.StockValue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BrokerUnitTest {
    @Mock
    IBroker iBroker;

    Calendar date;
    StockValue stockValue;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.date = Calendar.getInstance();
        this.stockValue = new StockValue(date, new BigDecimal(2), new BigDecimal(10));
    }

    @Test
    public void testGettersSetters() {
        Broker broker = new Broker();
        assertEquals(new BigDecimal(2), broker.getCommission());
    }

    @Test
    public void testPurchaseStocks() {
        BigDecimal customerAmount = new BigDecimal(22);
        when(iBroker.purchaseStocks(stockValue, customerAmount)).thenReturn(new BigDecimal(10));
        assertEquals(new BigDecimal(10), iBroker.purchaseStocks(stockValue, customerAmount));
    }

    @Test
    public void testSellStocks() {
        BigDecimal amountStocks = new BigDecimal(1000);
        when(iBroker.sellStocks(stockValue, amountStocks)).thenReturn(new BigDecimal(10));
        assertEquals(new BigDecimal(10), iBroker.sellStocks(stockValue, amountStocks));
    }
}
