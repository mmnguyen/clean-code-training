package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class CsvViewer {

    private Memory memory = new Memory();

    public void startCsvViewer(String[] args) throws IOException {

        String path = getCsvPath(args);
        List<CSVRecord> csvRecords = CsvReader.getRecords(path);
        List<Adress> adresses = new CsvReader().getAdresses(csvRecords);
        memory.setAdresses(adresses);

        int rowsPerPage = getRowsNumber(args);
        memory.setRows(rowsPerPage);
        List<Adress> adressesOnFirstPage = Pagination.getFirstPageAdresses(memory);
        UIViewer.updateTableView(adressesOnFirstPage);

        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        do {
            userInput = scanner.nextLine();
            switch (userInput.toUpperCase()) {
                case "F":
                    List<Adress> firstPageAdresses = Pagination.getFirstPageAdresses(memory);
                    UIViewer.updateTableView(firstPageAdresses);
                    break;
                case "P":
                    List<Adress> previousPageAdresses = Pagination.getPreviousPageAdresses(memory);
                    UIViewer.updateTableView(previousPageAdresses);
                    break;
                case "N":
                    List<Adress> nextPageAdresses = Pagination.getNextPageAdresses(memory);
                    memory.setPageNumber(memory.getPageNumber()+1);
                    UIViewer.updateTableView(nextPageAdresses);
                    break;
                case "L":
                    List<Adress> lastPageAdresses = Pagination.getLastPageAdresses(memory);
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
        AtomicReference<String> csvPath = new AtomicReference<>("");
        Arrays.stream(args).forEach(s -> {
            if (s.contains("csvpath=")) {
                csvPath.set(s.replace("csvpath=", ""));
            }
        });
        return csvPath.get();
    }

    private Integer getRowsNumber(String[] args) {
        AtomicReference<Integer> rowNumber = new AtomicReference<>(0);
        Arrays.stream(args).forEach(s -> {
            if (s.contains("row=")) {
                rowNumber.set(Integer.valueOf(s.replace("row=", "")));
            }
        });
        return rowNumber.get();
    }
}
