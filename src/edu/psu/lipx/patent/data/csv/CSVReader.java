package edu.psu.lipx.patent.data.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Peixuan Li Updated Nov 6, 2014
 */
public class CSVReader {

	BufferedReader bReader;
	String[] results;

	public CSVReader(File csvFile) throws FileNotFoundException {
		super();
		bReader = new BufferedReader(new FileReader(csvFile));
	}

	public String[] nextRow() throws IOException {
		String line;
		if ((line = bReader.readLine()) != null) {
			results = line.split("\t");
			return results;
		}
		return null;
	}

	public void close() throws IOException {
		bReader.close();
	}

}
