package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class CsvViewer {

    private Memory memory = new Memory();

    public void startCsvViewer(String[] args) throws IOException {

        String path = "adress.csv";
        List<CSVRecord> csvRecords = CsvReader.getRecords(path);
        memory.setCsvRecords(csvRecords);

        int rowsPerPage = getRowsNumber(args);
        memory.setRows(rowsPerPage);
        List<CSVRecord> adressesOnFirstPage = Pagination.getFirstPageAdresses(memory);
        UIViewer.updateTableView(adressesOnFirstPage);

        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        do {
            userInput = scanner.nextLine();
            switch (userInput.toUpperCase()) {
                case "F":
                    List<CSVRecord> firstPageAdresses = Pagination.getFirstPageAdresses(memory);
                    UIViewer.updateTableView(firstPageAdresses);
                    break;
                case "P":
                    List<CSVRecord> previousPageAdresses = Pagination.getPreviousPageAdresses(memory);
                    UIViewer.updateTableView(previousPageAdresses);
                    break;
                case "N":
                    List<CSVRecord> nextPageAdresses = Pagination.getNextPageAdresses(memory);
                    memory.setPageNumber(memory.getPageNumber()+1);
                    UIViewer.updateTableView(nextPageAdresses);
                    break;
                case "L":
                    List<CSVRecord> lastPageAdresses = Pagination.getLastPageAdresses(memory);
                    UIViewer.updateTableView(lastPageAdresses);
                    break;
                case "E":
                    scanner.close();
                    UIViewer.printGoodbye();
                    break;
                default:
                    UIViewer.printMenu();
                    break;
            }
        } while (!userInput.equals("E"));
        scanner.close();
    }

    private String getCsvPath(String[] args) {
        return args[0];
    }

    private Integer getRowsNumber(String[] args) {
        int row = Memory.DEFAULT_ROWS;
        if (args.length > 1) {
            row = Integer.valueOf(args[1]);
        }
        return row;
    }
}
