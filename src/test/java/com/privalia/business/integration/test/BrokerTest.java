package com.privalia.business.integration.test;

import com.privalia.model.Broker;
import com.privalia.model.IBroker;

import com.privalia.model.StockValue;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class BrokerTest {
    Calendar date;
    StockValue stockValue;

    @Before
    public void setUp() throws Exception {
        this.date = Calendar.getInstance();
        this.stockValue = new StockValue(date, new BigDecimal(2), new BigDecimal(10));
    }

    @Test
    public void testPurchaseStocks() {
        BigDecimal customerAmount = new BigDecimal(100);
        IBroker broker = new Broker();

        BigDecimal stocksPurchasedExpected = new BigDecimal(49).setScale(3, BigDecimal.ROUND_HALF_EVEN);

        assertEquals(stocksPurchasedExpected, broker.purchaseStocks(stockValue, customerAmount));
    }

    @Test
    public void testSellStocks() {
        BigDecimal amountStocks = new BigDecimal(1000);
        BigDecimal amountExpected = new BigDecimal(10000).setScale(3, BigDecimal.ROUND_HALF_EVEN);
        IBroker broker = new Broker();
        assertEquals(amountExpected, broker.sellStocks(stockValue, amountStocks));
    }
}
