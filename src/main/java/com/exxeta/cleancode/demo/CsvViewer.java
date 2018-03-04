package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class CsvViewer {

	private Memory memory = new Memory();
	private Boolean stopApplication;

	public void startCsvViewer(String[] args) throws IOException {
		// Read and store csv
		String filePath = getCsvPath(args);
		List<CSVRecord> csvRecords = CsvReader.getRecords(filePath);
		memory.setCsvRecords(csvRecords);

		// Store rows per page param
		int rowsPerPage = getRowsNumber(args);
		memory.setRows(rowsPerPage);

		// Print first page
		List<CSVRecord> firstPageRecords = Pagination.getFirstPageAdresses(memory);
		UIViewer.getInstance().printTableView(firstPageRecords);

		// Read-process-print loop
		stopApplication = false;
		do {
			String userInput = UIViewer.getInstance().getUserInput();
			processUserInput(userInput, memory, (List<CSVRecord> result) -> {
				// onSuccess
				UIViewer.getInstance().printTableView(result);
			}, () -> {
				// onError
				UIViewer.getInstance().printMenu();
			}, () -> {
				// onExit
				UIViewer.getInstance().applicationStop();
				stopApplication = true;
			});
		} while (!stopApplication);

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

	private void processUserInput(String userInput, Memory memory, Consumer<List<CSVRecord>> onSuccess,
			Runnable onError, Runnable onExit) {
		switch (userInput.toUpperCase()) {
		case "F":
			List<CSVRecord> firstPageRecords = Pagination.getFirstPageAdresses(memory);
			onSuccess.accept(firstPageRecords);
			break;
		case "P":
			List<CSVRecord> previousPageRecords = Pagination.getPreviousPageAdresses(memory);
			onSuccess.accept(previousPageRecords);
			break;
		case "N":
			List<CSVRecord> nextPageRecords = Pagination.getNextPageAdresses(memory);
			onSuccess.accept(nextPageRecords);
			break;
		case "L":
			List<CSVRecord> lastPageRecords = Pagination.getLastPageAdresses(memory);
			onSuccess.accept(lastPageRecords);
			break;
		case "E":
			onExit.run();
			break;
		default:
			onError.run();
			break;
		}
	}
}
