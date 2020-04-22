package com.abm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class AbmmTest
{
	protected  Logger log= LogManager.getLogger(AbmPagesTest.class);
	public  WebDriver driver;

	@BeforeMethod()
	 public void openBrowser()
	 {
	 System.setProperty("webdriver.gecko.driver", "C:\\\\Program Files\\\\gecko\\\\geckodriver.exe");
	// System.setProperty("webdriver.chrome.driver", "C:\\\\Program Files\\\\Chrome\\\\Chrome77\\\\chromedriver.exe");
	 //driver=new ChromeDriver();
	 driver=new FirefoxDriver();
	 driver.get("https://www.indeed.com/cmp/Abm-Industries,-Inc./reviews?start=0");
	 driver.manage().window().maximize();
	 }
	 @AfterMethod()
	 public void closeBrowser()
	 {
	 log.info("-------Closing the browser--------");
	 driver.close();
	 }
	 @Test()
	 public void printAdmPages() throws InterruptedException, Exception
	 {
	  String pagePath;
	  List<WebElement> subject, summary, ratings,desig_Date_Location, prosData, consData;
	  List<String> subjectList=new ArrayList<String>();
	  List<String> summaryList= new ArrayList<String>();
	  List<String> ratingsList= new ArrayList<String>();
	  List<String> desig_Date_LocationList= new ArrayList<String>();
	  List<String> prosConsList= new ArrayList<String>();
	  List<String>consList= new ArrayList<String>();
	  HashMap<String, String> alldata = new HashMap<String, String>();
	  /*Total pages are 8240*/
	  for(int i=0; i<=40; i=i+20) 
	  {
		  driver.get("https://www.indeed.com/cmp/Abm-Industries,-Inc./reviews?start="+i);
		
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  Thread.sleep(5000);
		  subject=driver.findElements(By.xpath("//div[@class='cmp-Review-container']/div[2]/div[1]"));
		  summary=driver.findElements(By.xpath("//div[@class='cmp-Review-container']/div[2]/div[3]"));
		  ratings=driver.findElements(By.xpath("//div[@class='cmp-Review-container']/div[1]/div[1]/div[1]"));
		  desig_Date_Location=driver.findElements(By.xpath("//div[@class='cmp-Review-container']/div[2]/div[2]"));
		  prosData=driver.findElements(By.xpath("//div[@class='cmp-Review-container']/div[2]/div[4]/div/div[1]"));
		  consData=driver.findElements(By.xpath("//div[@class='cmp-Review-container']/div[2]/div[4]/div/div[2]"));
		   for(WebElement contacts: subject)
		   {
			   subjectList.add(contacts.getText());
			   for(WebElement contact1 :summary)
			   {
				summaryList.add(contacts.getText());
			   }
			   // alldata.put(subjectList, summaryList);
		   }
		   
		   HashMap<String, String> storedata = getDataFromMap(summaryList,subjectList,alldata);
		 
		    for(Map.Entry<String, String> eachMapItem :storedata.entrySet() )
		     {
		      log.info(eachMapItem.getKey() +"=="+eachMapItem.getValue());
		 
			 }
	     }
	  FileUtils fil= new FileUtils();
		fil.printDataInExcel(alldata);
	 }

	 
	private HashMap<String, String> getDataFromMap(List<String> summaryList, List<String> subjectList,HashMap<String, String> alldata)
	{
		
		
		for (String summary: summaryList) {
			for (String subject : subjectList) {
				
				alldata.put(summary,subject);
				
			}
		}
		return alldata;
 }
	
	public void printABMData() throws Exception
	{
		File file= new File("C:\\Users\\Prod\\Documents\\ABMdata.xlsx");
		FileInputStream fis= new FileInputStream(file);
		
		XSSFWorkbook wb= new XSSFWorkbook();
		XSSFSheet sh= wb.createSheet();
	}

}