package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;
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
        uiViewer.updateView(firstPage()); //TODO pass only first-page addresses to the view
//        firstPage().forEach(System.out::println);
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
                rowNumber.set(Integer.valueOf(s.replace("row=", ""))+1);
            }
        });
        return rowNumber.get();
    }

    public List<Adress> firstPage() {
        //UIViewer uiViewer = new UIViewer();
        //uiViewer.updateView(ADDRESSES_WHICH_ARE_ON_THE_FIRST_PAGE);

        memory.setPageNumber(Pagination.getFirstPage());
        // calc how many page
        System.out.println("maximal pagination with thins applicaiton is " + memory.getAdresses().size() / memory.getRows());
        return getPageElements(0, memory.getRows());
    }

    public List<Adress> getPageElements(int page, int rows) {
        List<Adress> pageAdress = new ArrayList<>();
        for (int i = page; i < setPage(rows, page); i++) {
            pageAdress.add(memory.getAdresses().get(i));
        }
        return pageAdress;
    }

    private int setPage(int rows, int page) {
        return page == 0 ? rows : rows * page;
    }

    public void previousPage() {

    }

    public void nextpage() {

    }

    public void lastPage() {

    }
}
