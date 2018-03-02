package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class CsvReaderTest {

    @Test
    public void getRecords() throws IOException {
        String adressURL = "./src/test/resources/adress.csv";
        List<CSVRecord> csvRecords = CsvReader.getRecords(adressURL);
        Assert.assertNotNull(csvRecords);
        Assert.assertEquals("Steve", csvRecords.get(4).get(0));
    }

    @Test
    public void getAdresses() throws IOException {
        List<CSVRecord> csvRecords = CsvReader.getRecords("./src/test/resources/adress.csv");
        Assert.assertNotNull(csvRecords);
        Assert.assertEquals("Steve", csvRecords.get(4).get(0));
    }

}