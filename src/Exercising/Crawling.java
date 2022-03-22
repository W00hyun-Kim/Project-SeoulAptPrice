//package Exercising;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
////출처 : https://data.seoul.go.kr/dataList/OA-15818/S/1/datasetView.do 
//public class Crawling {
//	static ArrayList<String> resultList = new ArrayList<String>();
//	static ArrayList<String> value = new ArrayList<String>();
//	// ID, PATH 설정
//	public static final String DRIVER_ID = "webdriver.chrome.driver";
//	public static final String DRIVER_PATH = "C:\\WebCrawling/chromedriver.exe";
//	
//	public static void main(String[] args) {
//		Parsing split = new Parsing();	
//		CsvFileWriter fw = new CsvFileWriter();
//		
////		while(true) {
//		for (int i = 0; i < 2; i++) {
//						
//			System.setProperty(DRIVER_ID, DRIVER_PATH);
//			
//			//브라우저 전시되지 않게 하는 옵션
//			ChromeOptions chromeOptions = new ChromeOptions(); 
////			chromeOptions.addArguments("--headless"); 
////			chromeOptions.addArguments("--no-sandbox"); 
//			WebDriver driver = new ChromeDriver(chromeOptions);
//			
//			// 접속할 url
//			String base_url = "https://m.map.naver.com/#/search";
//			
//			try{
//				// 접속
//				driver.get(base_url);
//				// 페이지로 들어갑니다.
//				System.out.println(driver.getPageSource());
//				// 태그중에 첫번째 찾은거				
//				split.parsing();
//												
//				
//					try {Thread.sleep(2000);} catch (InterruptedException e) {} 																
//					driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/form/div/div[2]/div/span[1]/input")).sendKeys(""+split.arr[i]);
//					driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/form/div/div[2]/div/span[1]/input")).sendKeys(Keys.ENTER);
//					
//					try {Thread.sleep(2000);} catch (InterruptedException e) {} 		        
//					driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[3]/div/div/div/div/a[12]")).click();
//					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
//					driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[5]/label[2]")).click();				
//					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
//					//가까운 운동시설 이름
//					String name =driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]/div[1]/a/div/strong")).getText();
//					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
//					
//					driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]/div[2]/a[2]")).click();			
//					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
//					driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[1]/button[1]/span[2]")).click();
//					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
//					
//					driver.findElement(By.xpath("html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).clear();
//					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
//					driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).sendKeys(""+split.arr[i]);	
//					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
//					driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).sendKeys(Keys.ENTER);	
//					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
//					driver.findElement(By.xpath("html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/ul/li/div/div[1]/div/button[1]")).click();				
//					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
//					driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[3]/ul/li[3]/button/span[2]")).click();				
//					
//					//소요시간
//					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
//					String time =driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[3]/div/div/ul/li[1]/button/div[2]/div[1]/span[2]")).getText();
//					//거리
//					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
//					String distance =driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[3]/div/div/ul/li[1]/button/div[2]/div[2]")).getText();
//					
//					String add = "," + name + "," + time + "," + distance;
//					
//					int index = 0;
//					String result = "";
//					for (int j = 0; j < split.list.size(); j++) {
//						
//						String target = "" + split.list.get(i);
//						String hashsort = "" + split.arr[i];
//						if(target.contains(hashsort)) {							
//							index = j;
//							result = ""+split.list.get(index)+add;
//						}
//					}
//					fw.csv(result);
//				
//					
//				driver.quit();
//				System.out.println(resultList);
//								
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//		}	
//	} 	//while
//	
