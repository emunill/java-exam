package com.privalia.business.unit.test;

import com.privalia.model.StockValue;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StockValueUnitTest {

    @Test
    public void testGettersSetters() {
        Calendar date = Calendar.getInstance();
        StockValue stockValue = new StockValue();
        stockValue.setDate(date);
        assertTrue(date.equals(stockValue.getDate()));

        BigDecimal bd = new BigDecimal("7.7");
        stockValue.setOpenValue(bd);
        assertEquals(bd, stockValue.getOpenValue());
        stockValue.setCloseValue(bd);
        assertEquals(bd, stockValue.getCloseValue());

    }
}
