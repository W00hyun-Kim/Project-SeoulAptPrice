package Exercising;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class Parsing2 {
	public static void main(String[] args) throws Exception {
		writeCsvData("C:\\WebCrawling\\Hello.csv");
		readCsvData("C:\\WebCrawling\\(1월)아파트(매매)_실거래가_20220322.csv");
	}

	private static void writeCsvData(String path) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(path));
		String[] category = { "name", "category", "city" };
		String[] data1 = { "피자맨", "피자", "병점" };
		String[] data2 = { "치킨맨", "치킨", "오산" };
		String[] data3 = { "타코맨", "타코", "영통" };
		String[] data4 = { "망고맨", "카페", "광교" };
		writer.writeNext(category);
		writer.writeNext(data1);
		writer.writeNext(data2);
		writer.writeNext(data3);
		writer.writeNext(data4);
		writer.close();
	}

	private static void readCsvData(String path) throws Exception {
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			for (int i = 0; i < nextLine.length; i++) {
				System.out.print(nextLine[i] + " ");
			}
			System.out.println();
		}
	}
}