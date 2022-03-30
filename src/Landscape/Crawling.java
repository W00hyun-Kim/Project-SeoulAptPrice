package Landscape;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
 
//Resource : https://data.seoul.go.kr/dataList/
public class Crawling {
   static ArrayList<String> resultList = new ArrayList<String>();
   static ArrayList<String> value = new ArrayList<String>();
   static ArrayList<Integer> tmp = new ArrayList<Integer>();
   
   public static final String DRIVER_ID = "webdriver.chrome.driver";
   public static final String DRIVER_PATH = "C:\\WebCrawling/chromedriver.exe";
   
   public static void main(String[] args) {
      Parsing split = new Parsing();   
      split.parsing();
      CsvFileWriter fw = new CsvFileWriter();
      TimeConverter timeConvert = new TimeConverter();
      DistanceConverter distanceConvert = new DistanceConverter();
            
      try{
         
         String category = ""+split.list.get(0)+",음식점,소요시간(분),거리(m),카페,소요시간(분),거리(m),쇼핑,소요시간(분),거리(m),숙박,소요시간(분),거리(m),병원,소요시간(분),거리(m),"
                     + "은행,소요시간(분),거리(m),주유소,소요시간(분),거리(m),마트슈퍼,소요시간(분),거리(m),편의점,소요시간(분),거리(m),생활편의,소요시간(분),거리(m),명소,소요시간(분),거리(m),체육시설,소요시간(분),거리(m),"
                     + "영화공연,소요시간(분),거리(m),관공서,소요시간(분),거리(m)";
         
         fw.csv(category);
         
         String str [] = null;
         for (int i =93; i <=1105; i++) {      //<=split.list.size()=1105;
            tmp.add(i);
            System.out.println("first index:"+tmp.get(0));
            int first = tmp.get(0);
            System.setProperty(DRIVER_ID, DRIVER_PATH);
            ChromeOptions chromeOptions = new ChromeOptions(); 
            //display the browser or not            
//            chromeOptions.addArguments("--headless"); 
//            chromeOptions.addArguments(" --no-sandbox"); 
            WebDriver driver = new ChromeDriver(chromeOptions);
            
            String str1 = "" + split.address.get(i);
            String str2 = "" + split.address.get(i-1);
            
            //Check if current address is correct with address right before or not.
            if (str1.equals(str2)) {
 
               // if current address==address right before, use the same result value.
               String result = "" + split.list.get(i) + value.get(i-first-1);
               System.out.println(value.get(i-first-1));
               value.add(value.get(i-first-1));
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
               
//Category----------------[1]~[14]               
               
               for (int j = 1; j <=14; j++) {
                 System.out.println(j);             
                  String findValue = "";
                  try {Thread.sleep(3000);} catch (InterruptedException e) {} 
                  try {
                     driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[3]/div/div/div/div/a["+j+"]")).click();                  
                     try {Thread.sleep(3000);} catch (InterruptedException e) {}                   
                  } catch (Exception e) {
                     try {
                	  driver.findElement(By.xpath("html/body/div[4]/div[2]/div[3]/div[4]/div/div/a["+j+"]")).click();  }
                     catch(Exception e2) {
                         try {Thread.sleep(2000);} catch (InterruptedException e1) {} 
                         driver.get("https://m.map.naver.com/#/search");
                         System.out.println(driver.getPageSource());                                                 
                         driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/form/div/div[2]/div/span[1]/input")).sendKeys(""+split.address.get(i));
                         driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/form/div/div[2]/div/span[1]/input")).sendKeys(Keys.ENTER);                           
                         try {Thread.sleep(2000);} catch (InterruptedException e3) {} 
                    	 driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[3]/div/div/div/div/a["+j+"]")).click();
                    	 driver.findElement(By.xpath("html/body/div[4]/div[2]/div[3]/div[4]/div/div/a["+j+"]")).click();
                     }
                     try {Thread.sleep(2000);} catch (InterruptedException e1) {} 
                  } 
                    
                  //click distance sorting
                  try {
                	  driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[5]/label[2]")).click();
                  } catch (Exception e) {
                	  findValue = ",없음,없음,없음";
                	  resultList.add(findValue);
                	  driver.navigate().refresh();
                      Thread.sleep(4000);
                	  continue;
                  }
                  try {Thread.sleep(4000);} catch (InterruptedException e) {} 
                  
                  //get the name of the place
                  String name =driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]/div[1]/a/div/strong")).getText();
                  try {Thread.sleep(4000);} catch (InterruptedException e) {} 
                  
                  //click "navigation"(it takes up to 3~4s to loading full page)
                  driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]/div[2]/a[2]")).click();         
                  try {Thread.sleep(6000);} catch (InterruptedException e) {} 
                  
                  //click the starting point
                  try {
                     driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[1]/button[1]")).click();

                     try {Thread.sleep(4000);} catch (InterruptedException e) {}                                         
                  } catch(Exception e1) {
                      driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[1]/button[1]")).click();
                  }
                                    
                  //delete the input value web automatically provided
                  driver.findElement(By.xpath("html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).clear();
                  try {Thread.sleep(3000);} catch (InterruptedException e) {} 
                  
                  //input the address
                  driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).sendKeys(""+split.address.get(i));   
                  try {Thread.sleep(4000);} catch (InterruptedException e) {} 
                  driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).sendKeys(Keys.ENTER);   
                  try {Thread.sleep(4000);} catch (InterruptedException e) {} 

                  try{driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).sendKeys(Keys.ENTER);   
	                  } catch(Exception e1) {
	                      driver.findElement(By.xpath("html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).click();
	                      driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).sendKeys(Keys.ENTER);   
	                  }
                  
                  try {Thread.sleep(5000);} catch (InterruptedException e) {}                      
                  try {
                	  driver.findElement(By.xpath("html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/ul/li/div/div[1]/div/button[1]")).click();            
                  } catch(Exception e1) {
                      driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/input")).sendKeys(Keys.ENTER);   
                	  driver.findElement(By.xpath("html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/ul/li/div/div[1]/div/button[1]")).click();            
                  }
                  try {Thread.sleep(3000);} catch (InterruptedException e) {} 
                  try {
                     //click the "By walk" button.
                	 System.out.println("click bywalk");
                     driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[3]/ul/li[3]/button/span[2]")).click();            
                     driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[2]/div[3]/ul/li[3]/button/span[2]")).click();            
                     try {Thread.sleep(2000);} catch (InterruptedException e) {} 
                     
                     //Get time and distance value.
                     String time =driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[3]/div/div/ul/li[1]/button/div[2]/div[1]/span[2]")).getText();
                     try {Thread.sleep(2000);} catch (InterruptedException e) {} 
                     String distance =driver.findElement(By.xpath("/html/body/div[2]/div[1]/section[1]/div/div/div/div[2]/div[3]/div/div/ul/li[1]/button/div[2]/div[2]")).getText();
                     System.out.println("추출했음");
                     time = timeConvert.TimeConverter(time);
                     distance = distanceConvert.DistanceConverter(distance);
                  
                     findValue = "," + name + "," + time + "," + distance;
 
					} catch (Exception e) {
						String time = "0";
						String distance = "0";
						findValue = "," + name + "," + time + "," + distance;
	                     System.out.println("0추출했음");
					}

					resultList.add(findValue);
					try {Thread.sleep(5000);} catch (InterruptedException e) {}
						driver.navigate().refresh();
						driver.navigate().refresh();
						System.out.println("refresh");
						try {Thread.sleep(5000);} catch (InterruptedException e) {}

						driver.navigate().back();
						System.out.println("뒤로가기1");
						driver.navigate().back(); 
						System.out.println("뒤로가기2");
						driver.navigate().back(); 
						System.out.println("뒤로가기3");
						driver.navigate().back(); 


					

				}//category for문

			}
			System.out.println(resultList);

			String addValue = "";

			for (int k = 0; k < resultList.size(); k++) {
				addValue = addValue + resultList.get(k);
			}

			String save = "" + split.list.get(i) + addValue;
			System.out.println(save);
			fw.csv(save);
			value.add(addValue);
			resultList.clear();

			driver.quit();

		} // for문
	} catch (Exception e) {
		e.printStackTrace();
	}
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}

}