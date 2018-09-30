package com.privalia.model;

import java.math.BigDecimal;
import java.util.Calendar;

public class StockValue {
    private Calendar date;
    private BigDecimal openValue;
    private BigDecimal closeValue;

    public StockValue() {
    }

    public StockValue(Calendar date, BigDecimal openValue, BigDecimal closeValue) {
        this.date = date;
        this.openValue = openValue;
        this.closeValue = closeValue;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public BigDecimal getOpenValue() {
        return openValue;
    }

    public void setOpenValue(BigDecimal openValue) {
        this.openValue = openValue;
    }

    public BigDecimal getCloseValue() {
        return closeValue;
    }

    public void setCloseValue(BigDecimal closeValue) {
        this.closeValue = closeValue;
    }
}
