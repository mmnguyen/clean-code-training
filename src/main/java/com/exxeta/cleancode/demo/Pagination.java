package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
	
	private Integer currtentPageNumber;

    public Integer getCurrtentPageNumber() {
		return currtentPageNumber;
	}

	public void setCurrtentPageNumber(Integer currtentPageNumber) {
		this.currtentPageNumber = currtentPageNumber;
	}

	public List<CSVRecord> firstPage(Memory memory) {
    	List<CSVRecord> firstPageRecords = null;
    	int rowsPerPage = memory.getRows();
    	int numberOfAllRecords = memory.getCsvRecords().size();
    	
    	if(numberOfAllRecords >= rowsPerPage) {
    		int indexOfLastRecordOnFirstPage = rowsPerPage;
    		firstPageRecords = new ArrayList<CSVRecord>(memory.getCsvRecords().subList(0, indexOfLastRecordOnFirstPage));
    	}
    	else {
    		int indexOfLastRecordOnFirstPage = numberOfAllRecords - 1;
    		firstPageRecords = new ArrayList<CSVRecord>(memory.getCsvRecords().subList(0, indexOfLastRecordOnFirstPage+1));
    	}
    	
    	currtentPageNumber = 0;
        return firstPageRecords;
    }
    
    public List<CSVRecord> previousPage(Memory memory) {
    	List<CSVRecord> previousPageRecords = null;
    	int rowsPerPage = memory.getRows();
    	int indexOfFirstRecordOnCurrentPage = currtentPageNumber * rowsPerPage - rowsPerPage;
    	int indexOfFirstRecordOnPreviousPage = indexOfFirstRecordOnCurrentPage - rowsPerPage;
    	
    	if(currtentPageNumber > 1) {
    		previousPageRecords = new ArrayList<CSVRecord>(memory.getCsvRecords().subList(indexOfFirstRecordOnPreviousPage, indexOfFirstRecordOnCurrentPage));
    		currtentPageNumber -= 1;
    	}
    	else {
    		previousPageRecords = firstPage(memory);
    	}
    	
        return previousPageRecords;
    }
    
    public List<CSVRecord> nextPage(Memory memory) {
    	List<CSVRecord> nextPageRecords = null;
    	int rowsPerPage = memory.getRows();
    	int numberOfAllRecords = memory.getCsvRecords().size();
    	int indexOfLastRecordOnCurrentPage = currtentPageNumber * rowsPerPage;
    	int indexOfLastRecordOnNextPage = indexOfLastRecordOnCurrentPage + rowsPerPage;
    	
    	if(numberOfAllRecords > indexOfLastRecordOnNextPage + 1) {
    		nextPageRecords = new ArrayList<CSVRecord>(memory.getCsvRecords().subList(indexOfLastRecordOnCurrentPage+1, indexOfLastRecordOnNextPage+1));
    		currtentPageNumber += 1;
    	}
    	else {
    		nextPageRecords = lastPage(memory);
    	}
    	
        return nextPageRecords;
    }

    public List<CSVRecord> lastPage(Memory memory) {
    	List<CSVRecord> lastPageRecords = null;
    	int rowsPerPage = memory.getRows();
    	int numberOfAllRecords = memory.getCsvRecords().size();
    	int leftOverRecords = numberOfAllRecords % rowsPerPage;
    	
    	if(leftOverRecords==0) {
    		int indexOfFirstRecordOnLastPage = numberOfAllRecords-rowsPerPage;
			lastPageRecords = new ArrayList<CSVRecord>(memory.getCsvRecords().subList(indexOfFirstRecordOnLastPage, numberOfAllRecords));
    	}
    	else {
    		int indexOfFirstRecordOnLastPage = numberOfAllRecords-leftOverRecords;
    		lastPageRecords = new ArrayList<CSVRecord>(memory.getCsvRecords().subList(indexOfFirstRecordOnLastPage, numberOfAllRecords));
    	}
    	
    	currtentPageNumber = (int)Math.ceil((float)numberOfAllRecords / rowsPerPage);
        return lastPageRecords;
    }

}
