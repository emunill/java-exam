package com.privalia.model;

import com.privalia.util.CalendarUtil;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Broker implements IBroker {
    private BigDecimal commission;
    static final Logger logger = Logger.getLogger(Broker.class);

    public Broker() {
        this.setCommission(new BigDecimal(2));
    }

    public BigDecimal getCommission() {
        return commission;
    }
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    /**
     * Purchase stocks
     *
     * @param stockValue stock
     * @param customerAmount customer amount
     * @return
     */
    public BigDecimal purchaseStocks(StockValue stockValue, BigDecimal customerAmount) {
        BigDecimal purchaseComission = customerAmount.multiply(this.getCommission()).divide(new BigDecimal(100));
        BigDecimal amountToInvest = customerAmount.subtract(purchaseComission);
        BigDecimal stocksPurchased = amountToInvest.divide(stockValue.getOpenValue(), 3, RoundingMode.HALF_EVEN);

        this.logPurchase(stockValue, stocksPurchased);

        return stocksPurchased;
    }

    /**
     * Sell stocks
     * note: broker has no commission in sell operations
     *
     * @param stockValue stock
     * @param amountStocks amount of stocks to sell
     * @return amount of money
     */
    public BigDecimal sellStocks(StockValue stockValue, BigDecimal amountStocks) {
        BigDecimal totalAmount = amountStocks.multiply(stockValue.getCloseValue());
        totalAmount = totalAmount.setScale(3, BigDecimal.ROUND_HALF_EVEN);

        this.logSell(stockValue, amountStocks, totalAmount);

        return totalAmount;
    }

    /**
     * Purchase's log
     *
     * @param stockValue
     * @param stocksPurchased
     */
    private void logPurchase(StockValue stockValue, BigDecimal stocksPurchased) {
        StringBuilder sb = new StringBuilder("Compra de acciones -- fecha: ")
            .append(CalendarUtil.toString(stockValue.getDate()))
            .append("; Valor de compra: ")
            .append(stockValue.getOpenValue())
            .append("; Acciones compradas: ")
            .append(stocksPurchased);
        logger.info(sb);
    }

    /**
     * Sell's log
     *
     * @param stockValue
     * @param amountStocks
     * @param totalAmount
     */
    private void logSell(StockValue stockValue, BigDecimal amountStocks, BigDecimal totalAmount) {
        StringBuilder sb = new StringBuilder("Venta de acciones -- fecha: ")
            .append(CalendarUtil.toString(stockValue.getDate()))
            .append("; Valor de venta: ")
            .append(stockValue.getCloseValue())
            .append("; Acciones a vender: ")
            .append(amountStocks)
            .append("; Cantidad total recibida:")
            .append(totalAmount);
        logger.info(sb);
    }
}
