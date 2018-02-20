package com.exxeta.cleancode.demo;

public class Pagination {

    public static Integer getFirstPage() {
        return 0;
    }

    public static Integer getNextpage(Memory memory) {
        // TODO check if last page is reached
        return memory.getPageNumber() + 1;
    }
}
