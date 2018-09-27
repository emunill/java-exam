package com.privalia.util;

import com.opencsv.CSVReader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * CsvUtil class
 */
public class CsvUtil {
    static final Logger logger = Logger.getLogger(CsvUtil.class);

    private CsvUtil() {
    }

    public static List<String[]> read(String fileName) throws IOException {
        List<String[]> rows = null;
        try (
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            CSVReader csvReader = new CSVReader(reader);
        ) {
            rows = csvReader.readAll();
            logger.info("CSV read complete.");
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw e;
        }

        return rows;
    }
}
