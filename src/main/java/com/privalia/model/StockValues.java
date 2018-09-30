package com.privalia.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StockValues {
    private List<StockValue> stockValues;

    public StockValues() {
        this.stockValues = new ArrayList<>();
    }
    public StockValues(List<StockValue> stockValues) {
        this.stockValues = stockValues;
    }

    public List<StockValue> getStockValues() {
        return stockValues;
    }
    public void setStockValues(List<StockValue> stockValues) {
        this.stockValues = stockValues;
    }

    /**
     * add stock value to list
     *
     * @param stockValue
     */
    public void add(StockValue stockValue) {
        this.stockValues.add(stockValue);
    }

    /**
     * Returns the next stock value of date
     *
     * @param date
     * @return
     */
    public StockValue getNextStockValue(Calendar date) {
        List<StockValue> stockValues = this.getStockValues();

        for (int i = stockValues.size() -1; i >= 0; i--) {
            Calendar listCurrentStockValueDate = (Calendar)stockValues.get(i).getDate().clone();
            if (listCurrentStockValueDate.after(date)) {
                return stockValues.get(i);
            }
        }
        return null;
    }

    /**
     * Returns the stock value of date
     *
     * @param date
     * @return
     */
    public StockValue getStockValue(Calendar date) {
        StockValue stockValue = stockValues
                .stream()
                .filter(sv -> sv.getDate().equals(date))
                .findFirst()
                .get();

        return  stockValue;
    }
}
