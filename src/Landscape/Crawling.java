package Landscape;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//Resource : https://data.seoul.go.kr/dataList/OA-15818/S/1/datasetView.do 
public class Crawling {
	static ArrayList<String> resultList = new ArrayList<String>();
	static ArrayList<String> value = new ArrayList<String>();
	
	public static final String DRIVER_ID = "webdriver.chrome.driver";
	public static final String DRIVER_PATH = "C:\\WebCrawling/chromedriver.exe";
	
	public static void main(String[] args) {
		Parsing split = new Parsing();	
		split.parsing();
		CsvFileWriter fw = new CsvFileWriter();
				
		try{
			
			String category = ""+split.list.get(0)+"음식점,소요시간,거리,카페,소요시간,거리,쇼핑,소요시간,거리,숙박,소요시간,거리,병원의료,소요시간,거리"
					+ "은행,소요시간,거리,주유소,소요시간,거리,마트슈퍼,소요시간,거리,편의점,소요시간,거리,생활편의,소요시간,거리,명소,소요시간,거리,체육시설,소요시간,거리"
					+ "영화공연,소요시간,거리,관공서,소요시간,거리";
			fw.csv(category);
			
			String str [] = null;
			for (int i = 1; i <=3; i++) {		//<=split.list.size()=1105;
				System.setProperty(DRIVER_ID, DRIVER_PATH);
				ChromeOptions chromeOptions = new ChromeOptions(); 
				//display the browser or not
				
//				chromeOptions.addArguments("--headless"); 
//				chromeOptions.addArguments("--no-sandbox"); 
				WebDriver driver = new ChromeDriver(chromeOptions);
				
				String str1 = "" + split.address.get(i);
				String str2 = "" + split.address.get(i-1);
				
				//Check if current address is correct with address right before or not.
				if (str1.equals(str2)) {
					if(str[i-1]==null) {
						str[i-1]=str[i-2];
					}
				//if current address==address right before, use the same result value.
					String result = "" + split.list.get(i) + str[i - 1];
					fw.csv(result);
					driver.quit();
					continue;
				
				//if current address!=address before, crawling the result value.
				} else  {
					String base_url = "https://m.map.naver.com/#/search";
					driver.get(base_url);
					System.out.println(driver.getPageSource());	
			
					try {Thread.sleep(2000);} catch (InterruptedException e) {} 																
					driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/form/div/div[2]/div/span[1]/input")).sendKeys(""+split.address.get(i));
					driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/form/div/div[2]/div/span[1]/input")).sendKeys(Keys.ENTER);
						
					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
					
					
//Category----------------[1]
					
					String findValue = "";
										
					driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[3]/div/div/div/div/a[1]")).click();						
					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
											
					//click distance sorting
					driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[5]/label[2]")).click();				
					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
					
					//get the name of the place
					String name =driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]/div[1]/a/div/strong")).getText();
					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
					
					//click "navigation"(it takes up to 3~4s to loading full page)
					driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]/div[2]/a[2]")).click();			
					try {Thread.sleep(5000);} catch (InterruptedException e) {} 
					
					//click the starting point
					driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[1]/button[1]/span[2]")).click();
					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
					
					//delete the input value web automatically provided
					driver.findElement(By.xpath("html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).clear();
					try {Thread.sleep(3000);} catch (InterruptedException e) {} 
					
					//input the address
					driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).sendKeys(""+split.address.get(i));	
					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
					driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).sendKeys(Keys.ENTER);	
					try {Thread.sleep(2000);} catch (InterruptedException e) {} 							
					driver.findElement(By.xpath("html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/ul/li/div/div[1]/div/button[1]")).click();				
					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
					
					try {
						//click the "By walk" button.
						driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[3]/ul/li[3]/button/span[2]")).click();				
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						
						//Get time and distance value.
						String time =driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[3]/div/div/ul/li[1]/button/div[2]/div[1]/span[2]")).getText();
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						String distance =driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[3]/div/div/ul/li[1]/button/div[2]/div[2]")).getText();
						findValue =","+name+","+time+","+distance;
						
					} catch (Exception e) {
						String time = "0분";
						String distance = "0m";
						findValue = ","+name+","+time+","+distance;							
					}
					
					resultList.add(findValue);
					try {Thread.sleep(3000);} catch (InterruptedException e) {} 
					
					driver.navigate().back();
					driver.navigate().back();
					
//Category----------------[2]~[14]					
										
					for (int j = 2; j <=14; j++) {
						//click the cateogory [#1 ~ #14]	
						
						
						driver.findElement(By.xpath("html/body/div[4]/div[2]/div[3]/div[4]/div/div/a["+j+"]")).click();						
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
												
						//click distance sorting
						driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[5]/label[2]")).click();				
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						
						//get the name of the place
						String name2 =driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]/div[1]/a/div/strong")).getText();
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						
						//click "navigation"(it takes up to 3~4s to loading full page)
						driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]/div[2]/a[2]")).click();			
						try {Thread.sleep(5000);} catch (InterruptedException e) {} 
						
						//click the starting point
						driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[1]/button[1]/span[2]")).click();
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						
						//delete the input value web automatically provided
						driver.findElement(By.xpath("html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).clear();
						try {Thread.sleep(3000);} catch (InterruptedException e) {} 
						
						//input the address
						driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).sendKeys(""+split.address.get(i));	
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).sendKeys(Keys.ENTER);	
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 							
						driver.findElement(By.xpath("html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/ul/li/div/div[1]/div/button[1]")).click();				
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						
						try {
							//click the "By walk" button.
							driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[3]/ul/li[3]/button/span[2]")).click();				
							try {Thread.sleep(2000);} catch (InterruptedException e) {} 
							
							//Get time and distance value.
							String time =driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[3]/div/div/ul/li[1]/button/div[2]/div[1]/span[2]")).getText();
							try {Thread.sleep(2000);} catch (InterruptedException e) {} 
							String distance =driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[3]/div/div/ul/li[1]/button/div[2]/div[2]")).getText();
							findValue = ","+name2+","+time+","+distance;
							
						} catch (Exception e) {
							String time = "0분";
							String distance = "0m";
							findValue =  ","+name2+","+time+","+distance;							
						}
						
						resultList.add(findValue);
						try {Thread.sleep(3000);} catch (InterruptedException e) {} 

						driver.navigate().back();
						driver.navigate().back();

					}
																					
				} 	
				System.out.println(resultList);
				
				String addValue = "";
				
				for (int j = 0; j < resultList.size(); j++) {
					addValue = addValue+resultList.get(j);
				}
				
				String save = ""+split.list.get(i)+addValue;
				System.out.println(save);
				fw.csv(save);	
				
				driver.quit();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
	 	//while
	
