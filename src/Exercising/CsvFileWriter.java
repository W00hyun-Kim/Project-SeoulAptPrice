package Exercising;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class CsvFileWriter {

	public static void csv(String input) {
		String filePath = "C:\\WebCrawling/traffic.csv";
			
		
		File file = null;
		BufferedWriter bw =null;
		String NewLine = System.lineSeparator();
		
		try {
			file = new File(filePath);
			
			//계속 쌓일 수 있도록 true로 변경
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.newLine();
//			bw.write("날짜, 서울시 전체속도(km/h), 도심 전체속도(km/h)");
//			bw.newLine();
			bw.write(input);
			bw.flush();
			bw.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
