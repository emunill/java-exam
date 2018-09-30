package com.privalia.model;

import java.math.BigDecimal;

public interface IBroker {
    BigDecimal purchaseStocks(StockValue stockValue, BigDecimal customerAmount);
    BigDecimal sellStocks(StockValue stockValue, BigDecimal amountStocks);
}
