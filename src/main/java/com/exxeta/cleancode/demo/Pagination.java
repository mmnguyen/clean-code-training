package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.List;

public class Pagination {

    public static List<CSVRecord> getFirstPageAdresses(Memory memory) {
        memory.setPageNumber(0);
        return getPageSize(memory);
    }


    private static int getPageSize(int listSize, int currentPage, int pageSize) {
        currentPage ++;
        return Math.min(listSize, Math.abs(currentPage * pageSize));
    }

    private static int getEndPage(int listSize, int pageSize) {
        return Math.min(pageSize, listSize);
    }

    private static boolean hasNext(int listSize, int pageSize) {
        return getEndPage(pageSize, listSize) < listSize;
    }

    private static boolean hasPrevious(int listSize, int currentPage, int pageSize) {
        return getPageSize(listSize, currentPage, pageSize) > 0;
    }

    public static List<CSVRecord> getPreviousPageAdresses(Memory memory) {
        memory.setPageNumber(memory.getPageNumber()-1);
        if (!hasPrevious(memory.getCsvRecords().size(),memory.getPageNumber(), memory.getRows())) {
            return new ArrayList<>();
        }
        return getPageSize(memory);
    }

    public static List<CSVRecord> getLastPageAdresses(Memory memory) {
        List<CSVRecord> adressList = new ArrayList<>();
        int index = memory.getCsvRecords().size() - memory.getCsvRecords().size() % memory.getRows();
        for (int i = index; i < memory.getCsvRecords().size() ; i++) {
            adressList.add(memory.getCsvRecords().get(i));
        }

        return adressList;
    }

    private static List<CSVRecord> getPageSize(Memory memory) {
        List<CSVRecord> adressList = new ArrayList<>();
        int index = getPageSize(memory.getCsvRecords().size(), memory.getPageNumber(), memory.getRows()) - memory.getRows();
        for (int i = index; i < getPageSize(memory.getCsvRecords().size(), memory.getPageNumber(), memory.getRows()); i++) {
            adressList.add(memory.getCsvRecords().get(i));
        }

        return adressList;
    }

    public static List<CSVRecord> getNextPageAdresses(Memory memory) {
        memory.setPageNumber(memory.getPageNumber() + 1);
        if (!hasNext(memory.getCsvRecords().size(), memory.getRows())) {
            return new ArrayList<>();
        }
        return getPageSize(memory);
    }
}
