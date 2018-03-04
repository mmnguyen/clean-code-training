package com.exxeta.cleancode.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Test;

public class UIViewerTest {

	@Test
	public void getColumnWidthsTest() throws IOException {
        String filePath = "./src/test/resources/cars.csv";
        List<CSVRecord> csvRecords = CsvReader.getRecords(filePath);
		
        UIViewer uiViewer = UIViewer.getInstance();
        List<Integer> columnWidths = uiViewer.getColumnWidths(csvRecords);
        
        //Longest values per column in cars.csv are: "a-really-long-brand-name", "color"/"green", "medium", "tires"
        List<Integer> expectedColumnWidths =  new ArrayList<Integer>();
        expectedColumnWidths.add(24);
        expectedColumnWidths.add(5);
        expectedColumnWidths.add(6);
        expectedColumnWidths.add(5);
        
        Assert.assertTrue(columnWidths.equals(expectedColumnWidths));
	}
	
	@Test
	public void getColumnWidthsEmptyListTest() throws IOException {
        String filePath = "./src/test/resources/empty.csv";
        List<CSVRecord> csvRecords = CsvReader.getRecords(filePath);
		
        UIViewer uiViewer = UIViewer.getInstance();
        List<Integer> columnWidths = uiViewer.getColumnWidths(csvRecords);
        
        Assert.assertTrue(columnWidths == null);
	}
	
}
