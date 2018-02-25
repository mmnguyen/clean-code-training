package com.exxeta.cleancode.demo;

import java.util.ArrayList;
import java.util.List;

public class Pagination {

    public static List<Adress> getFirstPageAdresses(Memory memory) {
        memory.setPageNumber(0);
        return getPageElement(memory);
    }


    public static int getPageElement(int listSize, int currentPage, int pageSize) {
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
        return getPageElement(listSize, currentPage, pageSize) > 0;
    }

    public static List<Adress> getPreviousPageAdresses(Memory memory) {
        memory.setPageNumber(memory.getPageNumber()-1);
        if (!hasPrevious(memory.getAdresses().size(),memory.getPageNumber(), memory.getRows())) {
            return new ArrayList<>();
        }
        return getPageElement(memory);
    }

    public static List<Adress> getLastPageAdresses(Memory memory) {
        List<Adress> adressList = new ArrayList<>();
        int index = memory.getAdresses().size() - memory.getAdresses().size() % memory.getRows();
        for (int i = index; i < memory.getAdresses().size() ;i++) {
            adressList.add(memory.getAdresses().get(i));
        }

        return adressList;
    }

    private static List<Adress> getPageElement(Memory memory) {
        List<Adress> adressList = new ArrayList<>();
        int index = getPageElement(memory.getAdresses().size(), memory.getPageNumber(), memory.getRows()) - memory.getRows();
        for (int i = index; i < getPageElement(memory.getAdresses().size(), memory.getPageNumber(), memory.getRows()); i++) {
            adressList.add(memory.getAdresses().get(i));
        }

        return adressList;
    }

    public static List<Adress> getNextPageAdresses(Memory memory) {
        memory.setPageNumber(memory.getPageNumber() + 1);
        if (!hasNext(memory.getAdresses().size(), memory.getRows())) {
            return new ArrayList<>();
        }
        return getPageElement(memory);
    }
}
