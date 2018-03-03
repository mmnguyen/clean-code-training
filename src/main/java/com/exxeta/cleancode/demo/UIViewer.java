package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UIViewer {
	
	public static void updateTableView(List<CSVRecord> records) {
		List<Integer> columnWidths = getColumnWidths(records);
		List<String> table = createTable(records, columnWidths);
		printRows(table);
		List<String> menu = createMenu();
		printRows(menu);
	}
	
    public static void printMenu() {
    	System.out.println("F)irst	P)rev	N)ext	L)ast	E)xit");
    }
    
    public static void printGoodbye() {
    	System.out.println("Thanks for using CsvViewer! Godbye :)");
    }
	
	
    private static List<Integer> getColumnWidths(List<CSVRecord> records) {
    	int numberOfColumns = records.get(0).size();
    	List<Integer> columnWidths = new ArrayList<Integer>(Collections.nCopies(numberOfColumns, 0));
    	
    	for(CSVRecord record : records) {
    		for(int i = 0; i<record.size(); i++) {
    			if(record.get(i).length() > columnWidths.get(i)) {
    				columnWidths.set(i, record.get(i).length());
    			}
    		}
    	}
    	return columnWidths;
    }
    
    private static List<String> createTable(List<CSVRecord> records, List<Integer> columnWidths) {
    	List<String> table = new ArrayList<>();
    	table.add(createSeparatorRow(columnWidths));
    	for(CSVRecord record : records) {
    		String row = "";
    		for(int i = 0; i < record.size(); i++) {
        		row += "|";
        		row += record.get(i);
        		int numbreOfBlanksToFillCell = columnWidths.get(i) - record.get(i).length();
        		row += new String(new char[numbreOfBlanksToFillCell]).replace("\0", " ");
    		}
    		row += "|";
    		table.add(row);
    		table.add(createSeparatorRow(columnWidths));
    	}
    	return table;
    }

	private static String createSeparatorRow(List<Integer> columnWidths) {
		String separatorLine = "";
		for(int i = 0; i < columnWidths.size(); i++) {
			separatorLine += "+";
			separatorLine += new String(new char[columnWidths.get(i)]).replace("\0", "-");
    	}
		separatorLine += "+";
		return separatorLine;
	}
	
	private static List<String> createMenu(){
		List<String> menu = new ArrayList<>();
		menu.add("F)irst	P)rev	N)ext	L)ast	E)xit");
		return menu;
	}
	
	private static void printRows(List<String> rows) {
		for(String row : rows) {
			System.out.println(row);
		}
	}
    
}
