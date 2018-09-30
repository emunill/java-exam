package com.privalia.dao;

import com.privalia.model.StockValue;
import com.privalia.model.StockValues;
import com.privalia.util.CalendarUtil;
import com.privalia.util.CsvUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StockValueDao {
    static Properties prop;
    static InputStream input;
    static final Logger logger = Logger.getLogger(StockValueDao.class);

    static {
        prop = new Properties();
        try {
            input = StockValueDao.class.getResourceAsStream("/config.properties");
            prop.load(input);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * findAll
     *
     * @return
     * @throws IOException
     */
    private List<String[]> findAll() throws IOException {
        try {
            List<String[]> stockValues = CsvUtil.read(prop.getProperty("app.stocks.file"));
            stockValues.remove(0);

            return stockValues;
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    /**
     * Return a list of stock values
     *
     * @return
     * @throws IOException
     */
    public StockValues findAllStockValues () throws IOException{
        StockValues stockValues = new StockValues();
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

            List<String[]> rows = findAll();
            for (String[] row: rows) {
                String[] fields = row[0].split(";");
                Calendar day = Calendar.getInstance();
                day.setTime(format.parse(fields[0]));
                CalendarUtil.clearHour(day);
                stockValues.add(
                    new StockValue(
                        day,
                        new BigDecimal(fields[1]),
                        new BigDecimal(fields[2])
                    )
                );
            }

        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        catch (IOException e) {
            logger.error(e.getMessage());
            throw e;
        }

        return stockValues;
    }
}
