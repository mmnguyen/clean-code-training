package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public class Memory {

    public static Integer DEFAULT_ROWS = 5;

    private Integer pageNumber;
    private List<CSVRecord> csvRecords;
    private Integer rows;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public List<CSVRecord> getCsvRecords() {
        return csvRecords;
    }

    public void setCsvRecords(List<CSVRecord> csvRecords) {
        this.csvRecords = csvRecords;
    }
}
