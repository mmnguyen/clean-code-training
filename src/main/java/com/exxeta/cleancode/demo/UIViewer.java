package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public final class UIViewer {

	private static UIViewer instance = null;

	private Scanner scanner;

	private UIViewer() {
	}

	public static UIViewer getInstance() {
		if (instance == null) {
			instance = new UIViewer();
		}
		return instance;
	}

	public String getUserInput() {
		scanner = new Scanner(System.in);
		String userInput = "";
		try {
			userInput = scanner.nextLine();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return userInput;
	}

	public void printTableView(List<CSVRecord> records) {
		List<Integer> columnWidths = getColumnWidths(records);
		List<String> table = createTable(records, columnWidths);
		printRows(table);
		List<String> menu = createMenu();
		printRows(menu);
	}

	public void printMenu() {
		System.out.println("F)irst	P)rev	N)ext	L)ast	E)xit");
	}

	public void applicationStop() {
		printGoodbye();
		scanner.close();
	}

	private void printGoodbye() {
		System.out.println("Thanks for using CsvViewer! Godbye :)");
	}

	public List<Integer> getColumnWidths(List<CSVRecord> records) {
		int numberOfColumns;
		List<Integer> columnWidths;
		if(records.size()>0) {
			numberOfColumns = records.get(0).size();
			columnWidths = new ArrayList<Integer>(Collections.nCopies(numberOfColumns, 0));
		}
		else {
			return null;
		}

		for (CSVRecord record : records) {
			for (int i = 0; i < record.size(); i++) {
				if (record.get(i).length() > columnWidths.get(i)) {
					columnWidths.set(i, record.get(i).length());
				}
			}
		}
		return columnWidths;
	}

	private List<String> createTable(List<CSVRecord> records, List<Integer> columnWidths) {
		List<String> table = new ArrayList<>();
		table.add(createSeparatorRow(columnWidths));
		for (CSVRecord record : records) {
			String row = "";
			for (int i = 0; i < record.size(); i++) {
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

	private String createSeparatorRow(List<Integer> columnWidths) {
		String separatorLine = "";
		for (int i = 0; i < columnWidths.size(); i++) {
			separatorLine += "+";
			separatorLine += new String(new char[columnWidths.get(i)]).replace("\0", "-");
		}
		separatorLine += "+";
		return separatorLine;
	}

	private List<String> createMenu() {
		List<String> menu = new ArrayList<>();
		menu.add("F)irst	P)rev	N)ext	L)ast	E)xit");
		return menu;
	}

	private void printRows(List<String> rows) {
		for (String row : rows) {
			System.out.println(row);
		}
	}

}
