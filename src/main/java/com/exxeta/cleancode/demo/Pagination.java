package com.exxeta.cleancode.demo;

public class Pagination {

    public static Integer getFirstPage() {
        return 0;
    }

    public static Integer getPreviousPage(Memory memory) {
    	int currentPageNumberMinusOne = memory.getPageNumber() + 1;
        if (currentPageNumberMinusOne > 0) {
        	return currentPageNumberMinusOne;
        }
        return memory.getPageNumber();
    }
    
    public static Integer getNextPage(Memory memory) {
    	int currentPageNumberPlusOne = memory.getPageNumber() + 1;
        if (currentPageNumberPlusOne < getLastPage(memory)) {
        	return currentPageNumberPlusOne;
        }
        return memory.getPageNumber();
    }
    
    public static Integer getLastPage(Memory memory) {
    	return memory.getAdresses().size() / memory.getRows();
    }
}
