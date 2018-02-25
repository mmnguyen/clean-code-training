package com.exxeta.cleancode.demo;

import java.util.List;
import java.util.Scanner;

public class UIViewer {
	private static int nameColumnWidth, cityColumnWidth, streetColumnWidth;
	
	public static void updateTableView(List<Adress> adressesArg) {
		getColumnWidths(adressesArg);
		printTable(adressesArg);
		printMenu();
	}
	
    public static void printMenu() {
    	System.out.println("F)irst	P)rev	N)ext	L)ast	E)xit");
    }
    
    public static void printGoodbye() {
    	System.out.println("Thanks for using CsvViewer! Godbye :)");
    }
	
	
    private static void getColumnWidths(List<Adress> adresses) {
    	int maxNameLength = 0, maxCityLength = 0, maxStreetLength = 0;
    	for(Adress adress : adresses) {
    		if(adress.getName().length() > maxNameLength) {
    			maxNameLength = adress.getName().length();
    		}
    		if(adress.getCity().length() > maxCityLength) {
    			maxCityLength = adress.getCity().length();
    		}
    		if(adress.getStreet().length() > maxStreetLength) {
    			maxStreetLength = adress.getStreet().length();
    		}
    	}
    	nameColumnWidth = maxNameLength;
    	cityColumnWidth = maxCityLength;
    	streetColumnWidth = maxStreetLength;
    }
    
    private static void printTable(List<Adress> adresses) {
    	//Print first separator line
		System.out.print("+");
		System.out.print(new String(new char[nameColumnWidth]).replace("\0", "-"));
		System.out.print("+");
		System.out.print(new String(new char[cityColumnWidth]).replace("\0", "-"));
		System.out.print("+");
		System.out.print(new String(new char[streetColumnWidth]).replace("\0", "-"));
		System.out.println("+");
		
    	for(Adress adress : adresses) {
    		//Print content line
    		System.out.print("|");
    		System.out.print(adress.getName());
    		int numbreOfBlanksToFillNameCell = nameColumnWidth - adress.getName().length();
    		System.out.print(new String(new char[numbreOfBlanksToFillNameCell]).replace("\0", " "));
    		
    		System.out.print("|");
    		System.out.print(adress.getCity());
    		int numbreOfBlanksToFillCityCell = cityColumnWidth - adress.getCity().length();
    		System.out.print(new String(new char[numbreOfBlanksToFillCityCell]).replace("\0", " "));
    		
    		System.out.print("|");
    		System.out.print(adress.getStreet());
    		int numbreOfBlanksToFillStreetCell = streetColumnWidth - adress.getStreet().length();
    		System.out.print(new String(new char[numbreOfBlanksToFillStreetCell]).replace("\0", " "));
    		System.out.println("|");
    		
    		//Print separator line
    		System.out.print("+");
    		System.out.print(new String(new char[nameColumnWidth]).replace("\0", "-"));
    		System.out.print("+");
    		System.out.print(new String(new char[cityColumnWidth]).replace("\0", "-"));
    		System.out.print("+");
    		System.out.print(new String(new char[streetColumnWidth]).replace("\0", "-"));
    		System.out.println("+");
    	}
    }
    
}
