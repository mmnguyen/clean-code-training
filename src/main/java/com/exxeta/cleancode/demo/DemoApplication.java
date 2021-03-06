package com.exxeta.cleancode.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... strings) {
		CsvViewer csvViewer = new CsvViewer();
		try {
			csvViewer.startCsvViewer(strings);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
