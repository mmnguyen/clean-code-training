package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {

    public CSVParser readCsv() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("./adress.csv"));
        return new CSVParser(reader, CSVFormat.DEFAULT);
    }

    public List<Adress> getAdresses() throws IOException {
        return readCsv()
                .getRecords()
                .stream()
                .map(this::mapCsvRecordToAdress)
                .collect(Collectors.toList());
    }

    public Adress mapCsvRecordToAdress(CSVRecord csvRecord) {
        return new Adress(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2));
    }
}
