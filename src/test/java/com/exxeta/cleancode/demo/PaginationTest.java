package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class PaginationTest {

    @Test
    public void getLastPage() {
    }

    @Test
    public void getPageElement() throws IOException {
        Memory memory = createMemoryObject();
        int something = Pagination.getPageElement(memory.getAdresses().size(), memory.getPageNumber(), memory.getRows());
        Assert.assertEquals(20,  something);
    }

    @Test
    public void getFirstPageAdresses() throws IOException {
        Memory memory = createMemoryObject();
        List<Adress> adressList = Pagination.getFirstPageAdresses(memory);
        Assert.assertNotNull(adressList);
        Assert.assertEquals("Steve", adressList.get(2).getName());
    }

    @Test
    public void getNextPageAdresses () throws IOException {
        Memory memory = createMemoryObject();
        List<Adress> adressList = Pagination.getNextPageAdresses(memory);
        Assert.assertNotNull(adressList);
        Assert.assertEquals("Steve", adressList.get(3).getName());
    }

    @Test
    public void getLastPageAdresses() throws IOException {
        Memory memory = createMemoryObject();
        List<Adress> adressList = Pagination.getLastPageAdresses(memory);
        Assert.assertEquals(4, adressList.size());

    }

    @Test
    public void getPreviousPageAdresses() throws IOException {
        Memory memory = createMemoryObject();
        List<Adress> adressList = Pagination.getPreviousPageAdresses(memory);
        Assert.assertEquals("Paul", adressList.get(2).getName());
    }

    private Memory createMemoryObject() throws IOException {
        Memory memory = new Memory();
        List<CSVRecord> csvRecords = CsvReader.getRecords("./src/test/resources/adress.csv");
        List<Adress> adressList = new CsvReader().getAdresses(csvRecords);
        memory.setAdresses(adressList);
        memory.setRows(5);
        memory.setPageNumber(3);
        return memory;
    }
}