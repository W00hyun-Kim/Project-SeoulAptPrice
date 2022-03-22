package Exercising;
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

//��ó : https://data.seoul.go.kr/dataList/OA-15818/S/1/datasetView.do 
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
			
//			String category = "시군구,번지,본번,부번,단지명,전용면적(㎡),계약년월,계약일,거래금액(만원),층,건축년도,도로명,해제사유발생일,"
//					+ "거래유형,중개사소재지,명소,시간,거리";
//			fw.csv(category);
			
			String str [] = null;
			for (int i = 302; i <=1107; i++) {		//split.list.size();
				System.setProperty(DRIVER_ID, DRIVER_PATH);
				ChromeOptions chromeOptions = new ChromeOptions(); 
//				chromeOptions.addArguments("--headless"); 
//				chromeOptions.addArguments("--no-sandbox"); 
				WebDriver driver = new ChromeDriver(chromeOptions);
				//url	
				
				String str1 = "" + split.address.get(i);
				String str2 = "" + split.address.get(i-1);
				
				if (str1.equals(str2)) {
					if(str[i-1]==null) {
						str[i-1]=str[i-2];
					}
					String result = "" + split.list.get(i) + str[i - 1];
					fw.csv(result);
					driver.quit();
					continue;
					
				} else  {
					String base_url = "https://m.map.naver.com/#/search";
					driver.get(base_url);
					System.out.println(driver.getPageSource());	
			
					try {Thread.sleep(2000);} catch (InterruptedException e) {} 																
					driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/form/div/div[2]/div/span[1]/input")).sendKeys(""+split.address.get(i));
					driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/form/div/div[2]/div/span[1]/input")).sendKeys(Keys.ENTER);
						
					try {Thread.sleep(2000);} catch (InterruptedException e) {} 
					
					
//					ArrayList<String> categ = new ArrayList<String>();
//					for (int j = 1; j <=14; j++) {
						
						driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[3]/div/div/div/div/a[11]")).click();
						
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[5]/label[2]")).click();				
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						
						String name =driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]/div[1]/a/div/strong")).getText();
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						
						driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]/div[2]/a[2]")).click();			
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[1]/button[1]/span[2]")).click();
						try {Thread.sleep(4000);} catch (InterruptedException e) {} 
						
						driver.findElement(By.xpath("html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).clear();
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).sendKeys(""+split.address.get(i));	
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).sendKeys(Keys.ENTER);	
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						driver.findElement(By.xpath("html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/ul/li/div/div[1]/div/button[1]")).click();				
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[3]/ul/li[3]/button/span[2]")).click();				
						
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						String time =driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[3]/div/div/ul/li[1]/button/div[2]/div[1]/span[2]")).getText();
						
						try {Thread.sleep(2000);} catch (InterruptedException e) {} 
						String distance =driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[3]/div/div/ul/li[1]/button/div[2]/div[2]")).getText();
						
						String add = "," + name + "," + time + "," + distance;
						str = new String[split.address.size()];
						str[i]=add;
						String result = ""+split.list.get(i)+add;
						fw.csv(result);
//						categ.add(add);
//						driver.navigate().back();
//						driver.navigate().back();
//						
//					}					
//					str = new String[split.address.size()];
//					
//					String info = null;
//					for(int k=0; k<categ.size(); k++) {
//						info =""+categ.get(k);
//					}					
//					str[i]=info;
//					String result = ""+split.list.get(i)+info;
//					fw.csv(result);
				} 
					
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
	
