package com.exxeta.cleancode.demo;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
    
    public static List<Adress> getFirstPageAdresses(Memory memory) {
    	int firstPageIndex = getFirstPageIndex();
        memory.setPageNumber(firstPageIndex);
        
        int rowsPerPage = memory.getRows();
        List<Adress> adressesOnFirstPage =  getPageElements(firstPageIndex, rowsPerPage, memory);
        
        return adressesOnFirstPage;
    }

    public static List<Adress> getPreviousPageAdresses(Memory memory) {
    	int previousPageIndex = getPreviousPageIndex(memory);
        memory.setPageNumber(previousPageIndex);
        
        int rowsPerPage = memory.getRows();
        List<Adress> adressesOnPreviousPage =  getPageElements(previousPageIndex, rowsPerPage, memory);
        
        return adressesOnPreviousPage;
    }

    public static List<Adress> getNextPageAdresses(Memory memory) {
    	int nextPageIndex = getNextPageIndex(memory);
        memory.setPageNumber(nextPageIndex);
        
        int rowsPerPage = memory.getRows();
        List<Adress> adressesOnNextPage =  getPageElements(nextPageIndex, rowsPerPage, memory);
        
        return adressesOnNextPage;
    }

    public  static List<Adress> getLastPageAdresses(Memory memory) {
    	int lastPageIndex = getNextPageIndex(memory);
        memory.setPageNumber(lastPageIndex);
        
        int rowsPerPage = memory.getRows();
        List<Adress> adressesOnLastPage =  getPageElements(lastPageIndex, rowsPerPage, memory);
        
        return adressesOnLastPage;
    }
    
    private static Integer getFirstPageIndex() {
        return 0;
    }

    private static Integer getPreviousPageIndex(Memory memory) {
    	int currentPageNumberMinusOne = memory.getPageNumber() - 1;
        if (currentPageNumberMinusOne > 0) {
        	return currentPageNumberMinusOne;
        }
        return memory.getPageNumber();
    }
    
    private static Integer getNextPageIndex(Memory memory) {
    	int currentPageNumberPlusOne = memory.getPageNumber() + 1;
        if (currentPageNumberPlusOne < getLastPage(memory)) {
        	return currentPageNumberPlusOne;
        }
        return memory.getPageNumber();
    }
    
    public static Integer getLastPage(Memory memory) {
    	return memory.getAdresses().size() / memory.getRows();
    }
    
    private static List<Adress> getPageElements(int page, int rows, Memory memory) {
        List<Adress> pageAdress = new ArrayList<>();
        for (int i = page; i < setPage(rows, page); i++) {
            pageAdress.add(memory.getAdresses().get(i));
        }
        return pageAdress;
    }

    private static int setPage(int rows, int page) {
        return page == 0 ? rows : rows * page;
    }
}
