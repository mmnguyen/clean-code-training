package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CsvViewer {

    private Memory memory = new Memory();

    public void startCsvViewer(String[] args) throws IOException {
        // Do some shit with args
        // get filename & get rawline number
        // TODO read rows & csv path

        String path = getCsvPath(args);
        List<CSVRecord> csvRecords = CsvReader.getRecords(path);
        memory.setAdresses(new CsvReader().getAdresses(csvRecords));
        memory.setRows(getRowsNumber(args));
        memory.setPageNumber(Pagination.getFirstPage());
//        PageManager.extractPage(memory);
        UIViewer uiViewer = new UIViewer();
        uiViewer.updateView(memory.getAdresses()); //TODO pass only first-page addresses to the view
    }

    private String getCsvPath(String [] args) {
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

    public void firstPage() {
        //UIViewer uiViewer = new UIViewer();
        //uiViewer.updateView(ADDRESSES_WHICH_ARE_ON_THE_FIRST_PAGE);
    }

    public void previousPage() {

    }

    public void nextpage() {

    }

    public void lastPage() {

    }
}
