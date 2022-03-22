package Exercising;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class Parsing {
	static ArrayList<String> list = new ArrayList<String>();
	static HashSet<String> address = new HashSet<String>();
	
	
	public static String[] str;
	public static Object[] arr;
//	public void parsing() {
	public static void main(String[] args) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\WebCrawling\\(1월)아파트(매매)_실거래가_20220322.csv"));
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
				str = line.split(",");
				address.add(str[11]);
			}
			
			arr =address.toArray();
//			System.out.println(Arrays.toString(arr)); //[천호대로 1077, 화곡로18길 24, 장한로26나길 3, 마포대로24길 16, 마들로 31, 문정로 125, 신정로7길 76, 강서로56길 64, 응암로 28, 상도로15길 131, 신수로3길 23, 서강로3길 32, 행당로 82, 올림픽로70길 61, 성내천로 103, 장문로 141, 구의강변로1길 44
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


}