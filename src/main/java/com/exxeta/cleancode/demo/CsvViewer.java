package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CsvViewer {

    private Memory memory = new Memory();

    public static void runApplication(String[] args) {
        if (args.length > 0) {
            System.out.println("Do something fancy ");
            Arrays.stream(args).forEach(s -> {
                if (s.contains("csvpath=")) {
                    System.out.println(s.replace("csvpath=", ""));
                }
            });
        } else {
            System.out.println("hmm args is empty");
        }
    }

    public void startCsvViewer(String args) throws IOException {
        // Do some shit with args
        // get filename & get rawline number
        // TODO read rows & csv path

        String path = "";
        List<CSVRecord> csvRecords = CsvReader.getRecords(path);
        memory.setAdresses(new CsvReader().getAdresses(csvRecords));
        memory.setRows(5);
        memory.setPageNumber(Pagination.getFirstPage());
//        PageManager.extractPage(memory);
    }

    public void firstPage() {

    }

    public void previousPage() {

    }

    public void nextpage() {

    }

    public void lastPage() {

    }
}
