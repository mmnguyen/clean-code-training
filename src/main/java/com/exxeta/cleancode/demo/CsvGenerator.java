package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CsvGenerator {
    // Generate CSV with some adresses
    private static final String SAMPLE_CSV_FILE = "./adress.csv";

    private List<String> names = new ArrayList<>(Arrays.asList("Paul", "Hans", "Steve", "Maria", "Paula", "Sarah", "Martin", "Lena", "Julia", "Steve", "Sabrina"));
    private List<String> street = new ArrayList<>(Arrays.asList("Weg 5", "Albert-Nestler-Str. 19", "Europaplatz 4", "forreststr. 4"));
    private List<String> city = new ArrayList<>(Arrays.asList("Stuttgart", "Karlsruhe", "Berlin", "Cologne", "Munich", "Bremen", "Hannover", "Erfurt", "Frankfurt"));


    public void generated (Integer numberofLines) throws IOException {
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));
        CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT.withHeader("name", "street", "city"));
        for (int i = 0; i < numberofLines; i++) {
            Random random = new Random();
            csvPrinter.printRecord(names.get(random.nextInt(names.size())), street.get(random.nextInt(street.size())), city.get(random.nextInt(city.size())));
        }
        csvPrinter.flush();
    }

    public static void main(String [] args) {
        try {
            new CsvGenerator().generated(50);
        } catch (IOException e) {
            System.out.println("Error happens");
        }
    }
}
