package com.exxeta.cleancode.demo;

import java.util.List;
import java.util.Scanner;

public class UIViewer {
	List<Adress> adresses;
	int nameColumnWidth, cityColumnWidth, streetColumnWidth;
	
	public void updateView(List<Adress> adresses) {
		this.adresses = adresses;
		this.getColumnWidths();
		this.printTable();
		this.printMenu();
	}
	
    public void getColumnWidths() {
    	int maxNameLength = 0, maxCityLength = 0, maxStreetLength = 0;
    	for(Adress adress : this.adresses) {
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
    	this.nameColumnWidth = maxNameLength;
    	this.cityColumnWidth = maxCityLength;
    	this.streetColumnWidth = maxStreetLength;
    }
    
    public void printTable() {
    	//Print first separator line
		System.out.print("+");
		System.out.print(new String(new char[nameColumnWidth]).replace("\0", "-"));
		System.out.print("+");
		System.out.print(new String(new char[cityColumnWidth]).replace("\0", "-"));
		System.out.print("+");
		System.out.print(new String(new char[streetColumnWidth]).replace("\0", "-"));
		System.out.println("+");
		
    	for(Adress adress : this.adresses) {
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
    
    public void printMenu() {
    	System.out.println("F)irst	P)rev	N)ext	L)ast	E)xit");
    }
    
    public void sayGoodbye() {
    	System.out.println("Thanks for using CsvViewer! Godbye :)");
    }
    
	public void getUserInput(CsvViewer csvViewer) {
		Scanner scanner = new Scanner(System.in);
		String userInput = "";
		userInput = scanner.nextLine();
        switch (userInput) {
        	case "F":
	            csvViewer.firstPage();
	            scanner.close();
	            break;
        	case "P":
	            csvViewer.previousPage();
	            scanner.close();
	            break;
        	case "N":
	            csvViewer.nextpage();
	            scanner.close();
	            break;
        	case "L":
	            csvViewer.lastPage();
	            scanner.close();
	            break;
        	case "E":
	            this.sayGoodbye();
	            scanner.close();
	            break;
	        default:
	        	this.printMenu();
	        	scanner.close();
	        	break;
        }
	}
	
}
