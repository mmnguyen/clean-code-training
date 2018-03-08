package com.exxeta.cleancode.demo;

import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class CsvViewer {

	private Memory memory = new Memory();
	private Pagination pagination = new Pagination();
	private Boolean stopApplication;

	private List<CSVRecord> getRecordswithoutHeader(List<CSVRecord> csvRecords) {
		this.memory.setHeaderLine(csvRecords.get(0));
		csvRecords.remove(memory.getHeaderLine());
		return csvRecords;
	}

	public void startCsvViewer(String[] args) throws IOException {
		//Read and store csv
		String filePath = getCsvPath(args);
		List<CSVRecord> csvRecords = CsvReader.getRecords(filePath);
		List<CSVRecord> recordsWithoutHeader = getRecordswithoutHeader(csvRecords);
		memory.setCsvRecords(recordsWithoutHeader);
		
		//Store rows per page param
		int rowsPerPage = getRowsNumber(args);
		memory.setRowsPerPage(rowsPerPage);
		
		//Print first page
		List<CSVRecord> firstPageRecords = pagination.firstPage(memory);
		UIViewer.getInstance().printTableView(firstPageRecords, memory.getHeaderLine());

		//Read-process-print loop
		stopApplication = false;
		do {
			String userInput = UIViewer.getInstance().getUserInput();
			processUserInput(userInput, memory, (List<CSVRecord> result) -> {
				//onSuccess
				UIViewer.getInstance().printTableView(result, memory.getHeaderLine());
			}, () -> {
				//onError
				UIViewer.getInstance().printMenu();
			}, () -> {
				//onExit
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
		if (memory.getCsvRecords().size() < row) {
			row = memory.getCsvRecords().size();
		}
		return row;
	}
	
	private void processUserInput(String userInput, Memory memory, Consumer<List<CSVRecord>> onSuccess, Runnable onError, Runnable onExit) {
		switch (userInput.toUpperCase()) {
		case "F":
			List<CSVRecord> firstPageRecords = pagination.firstPage(memory);
			onSuccess.accept(firstPageRecords);
			break;
		case "P":
			List<CSVRecord> previousPageRecords = pagination.previousPage(memory);
			onSuccess.accept(previousPageRecords);
			break;
		case "N":
			List<CSVRecord> nextPageRecords = pagination.nextPage(memory);
			onSuccess.accept(nextPageRecords);
			break;
		case "L":
			List<CSVRecord> lastPageRecords = pagination.lastPage(memory);
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
