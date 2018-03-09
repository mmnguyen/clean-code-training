package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public class Memory {

    public static Integer DEFAULT_ROWS = 5;

    private List<CSVRecord> csvRecords;
    private CSVRecord headerLine;
    private Integer rowsPerPage;

    public Integer getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(Integer rows) {
        this.rowsPerPage = rows;
    }

    public CSVRecord getHeaderLine() {
        return headerLine;
    }

    public void setHeaderLine(CSVRecord headerLine) {
        this.headerLine = headerLine;
    }

    public List<CSVRecord> getCsvRecords() {
        return csvRecords;
    }

    public void setCsvRecords(List<CSVRecord> csvRecords) {
        this.csvRecords = csvRecords;
    }
}
