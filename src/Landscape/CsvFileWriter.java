package Landscape;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvFileWriter {

	public static void csv(String input) {

		 File csv = new File("C:\\WebCrawling\\Test.csv");
	        BufferedWriter bw = null; 
			try {
				bw = new BufferedWriter(new FileWriter(csv, true));
				bw.write(input);
				bw.newLine();

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (bw != null) {
	                    bw.flush(); 
	                    bw.close(); 
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	}
}
