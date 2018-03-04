package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class PaginationTest {
	
	private Memory setUpMemory() throws IOException {
		Memory memory = new Memory();

		//Read and store csv
        String filePath = "./src/test/resources/adress.csv";
        List<CSVRecord> csvRecords = CsvReader.getRecords(filePath);
		memory.setCsvRecords(csvRecords);
		
		//Store rows per page param
		memory.setRowsPerPage(9);
		
		return memory;
	}

    @Test
    public void nextPageInMiddleOfDocumentTest() throws IOException {
    	Memory memory = setUpMemory();
    	Pagination pagination = new Pagination();
    	pagination.setCurrtentPageIndex(2);
    	
    	//Since adress.csv contains 49 records page 3 should be a full page containing 9 records
    	List<CSVRecord> nextPageRecords = pagination.nextPage(memory);
    	Assert.assertEquals(9, nextPageRecords.size());
    	//Page 3 should contain records 27-35 from the csv-file
    	List<CSVRecord> expectedRecords = memory.getCsvRecords().subList(27, 36);
    	Assert.assertTrue(nextPageRecords.equals(expectedRecords));
    }
    
    @Test
    public void nextPageAtEndOfDocumentTest() throws IOException {
    	Memory memory = setUpMemory();
    	Pagination pagination = new Pagination();
    	pagination.setCurrtentPageIndex(4);
    	
    	//Since adress.csv contains 49 records page 5 should be the last page containing only 4 records
    	List<CSVRecord> nextPageRecords = pagination.nextPage(memory);
    	Assert.assertEquals(4, nextPageRecords.size());
    	//Page 5 should contain records 45-48 from the csv-file
    	List<CSVRecord> expectedRecords = memory.getCsvRecords().subList(45, 49);
    	Assert.assertTrue(nextPageRecords.equals(expectedRecords));
    }
    
    @Test
    public void previousPageInMiddleOfDocumentTest() throws IOException {
    	Memory memory = setUpMemory();
    	Pagination pagination = new Pagination();
    	pagination.setCurrtentPageIndex(2);
    	
    	//Since adress.csv contains 49 records page 1 should be a full page containing 9 records
    	List<CSVRecord> nextPageRecords = pagination.previousPage(memory);
    	Assert.assertEquals(9, nextPageRecords.size());
    	//Page 1 should contain records 9-17 from the csv-file
    	List<CSVRecord> expectedRecords = memory.getCsvRecords().subList(9, 18);
    	Assert.assertTrue(nextPageRecords.equals(expectedRecords));
    }
    
    @Test
    public void previousPageAtBeginningOfDocumentTest() throws IOException {
    	Memory memory = setUpMemory();
    	Pagination pagination = new Pagination();
    	pagination.setCurrtentPageIndex(1);
    	
    	//Since adress.csv contains 49 records page 0 should be a full page containing 9 records
    	List<CSVRecord> nextPageRecords = pagination.previousPage(memory);
    	Assert.assertEquals(9, nextPageRecords.size());
    	//Page 0 should contain records 0-8 from the csv-file
    	List<CSVRecord> expectedRecords = memory.getCsvRecords().subList(0, 9);
    	Assert.assertTrue(nextPageRecords.equals(expectedRecords));
    }
    
    @Test
    public void lastPageInMiddleOfDocumentTest() throws IOException {
    	Memory memory = setUpMemory();
    	Pagination pagination = new Pagination();
    	pagination.setCurrtentPageIndex(3);
    	
    	//Since adress.csv contains 49 records the last page should contain only 4 records
    	List<CSVRecord> lastPageRecords = pagination.lastPage(memory);
    	Assert.assertEquals(4, lastPageRecords.size());
    	//The last page should contain records 45-48 from the csv-file
    	List<CSVRecord> expectedRecords = memory.getCsvRecords().subList(45, 49);
    	Assert.assertTrue(lastPageRecords.equals(expectedRecords));
    }
    
    @Test
    public void lastPageOnLastPageTest() throws IOException {
    	Memory memory = setUpMemory();
    	Pagination pagination = new Pagination();
    	pagination.setCurrtentPageIndex(5);
    	
    	//Since adress.csv contains 49 records the last page should contain only 4 records
    	List<CSVRecord> lastPageRecords = pagination.lastPage(memory);
    	Assert.assertEquals(4, lastPageRecords.size());
    	//The last page should contain records 45-48 from the csv-file
    	List<CSVRecord> expectedRecords = memory.getCsvRecords().subList(45, 49);
    	Assert.assertTrue(lastPageRecords.equals(expectedRecords));
    }
    
    @Test
    public void firstPageInMiddleOfDocumentTest() throws IOException {
    	Memory memory = setUpMemory();
    	Pagination pagination = new Pagination();
    	pagination.setCurrtentPageIndex(4);
    	
    	//Since adress.csv contains 49 records page 0 should be a full page containing 9 records
    	List<CSVRecord> firstPageRecords = pagination.firstPage(memory);
    	Assert.assertEquals(9, firstPageRecords.size());
    	//Page 0 should contain records 0-8 from the csv-file
    	List<CSVRecord> expectedRecords = memory.getCsvRecords().subList(0, 9);
    	Assert.assertTrue(firstPageRecords.equals(expectedRecords));
    }
    
    @Test
    public void firstPageOnFirstPageTest() throws IOException {
    	Memory memory = setUpMemory();
    	Pagination pagination = new Pagination();
    	pagination.setCurrtentPageIndex(0);
    	
    	//Since adress.csv contains 49 records the first page should be a full page containing 9 records
    	List<CSVRecord> firstPageRecords = pagination.firstPage(memory);
    	Assert.assertEquals(9, firstPageRecords.size());
    	//Page 0 should contain records 0-8 from the csv-file
    	List<CSVRecord> expectedRecords = memory.getCsvRecords().subList(0, 9);
    	Assert.assertTrue(firstPageRecords.equals(expectedRecords));
    }

}