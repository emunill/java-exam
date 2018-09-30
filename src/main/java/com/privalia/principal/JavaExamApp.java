package com.privalia.principal;

import com.privalia.dao.StockValueDao;
import com.privalia.model.Broker;
import com.privalia.model.StockValue;
import com.privalia.model.StockValues;
import com.privalia.util.CalendarUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Java exam
 * @author Eduard Munill
 */
public class JavaExamApp {
    static final Logger logger = Logger.getLogger(JavaExamApp.class);

    public static void main(String[] args)
    {
        Broker broker = new Broker();
        BigDecimal totalStocks = new BigDecimal(0);
        BigDecimal amountInvestment = new BigDecimal(50);
        StockValueDao stockValueDao = new StockValueDao();
        StockValue stockValue;

        try {
            StockValues stockValues = stockValueDao.findAllStockValues();

            Calendar lastDate = Calendar.getInstance();
            lastDate.set(2017, Calendar.DECEMBER,28);
            CalendarUtil.clearHour(lastDate);

            Calendar currentDate = Calendar.getInstance();
            int lastDayOfFirstMonth = CalendarUtil.getDayOfLastWeekDayOfMonth(Calendar.THURSDAY, Calendar.MAY, 2001);
            currentDate.set(2001, Calendar.MAY, lastDayOfFirstMonth);
            CalendarUtil.clearHour(currentDate);

            while (lastDate.after(currentDate)) {
                stockValue = stockValues.getNextStockValue(currentDate);
                if (stockValue != null) {
                    BigDecimal amountStocks = broker.purchaseStocks(stockValue, amountInvestment);
                    totalStocks = totalStocks.add(amountStocks);
                    currentDate = CalendarUtil.getDateOfLastWeekDayOfNextMonth(Calendar.THURSDAY, currentDate);
                }
            }

            stockValue = stockValues.getStockValue(lastDate);
            BigDecimal totalInvested = broker.sellStocks(stockValue, totalStocks);

            logger.info(new StringBuilder("Importe total: ").append(totalInvested));

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
