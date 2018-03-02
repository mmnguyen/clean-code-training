package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {

    private static CSVParser readCsv(String csvPath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(csvPath));
        return new CSVParser(reader, CSVFormat.DEFAULT);
    }

    public static List<CSVRecord> getRecords(String csvPath) throws IOException {
        return readCsv(csvPath).getRecords();
    }
}
