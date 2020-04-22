package com.abm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AbmPagesTest
{
	protected  Logger log= LogManager.getLogger(AbmPagesTest.class);
	public  WebDriver driver;
	private Cell firstdata;
	 @BeforeMethod()
	 public void openBrowser()
	 {
	 log.debug("-------Launching the browser--------");
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
	  List<WebElement> wholeData=null;
	  List<WebElement> totalProssCons=null;
	  List<WebElement> subject;
	  List<WebElement> summary = null;
	  List<WebElement> ratings=null;
	  List<WebElement> desig_Date_Location=null;
	  List<WebElement> prosData=null;
	  List<WebElement> consData=null;
 	  List<String> subjectList=new ArrayList<String>();
	  List<String> summaryList= new ArrayList<String>();
	  List<String> ratingsList= new ArrayList<String>();
	  List<String> desig_Date_LocationList= new ArrayList<String>();
	  List<String> prosConsList= new ArrayList<String>();
	  List<String> totalProssConsData= new ArrayList<String>();
	  /*Total pages are 8240*/
	  for(int i=0; i<=80; i=i+20) 
	  {
		  driver.get("https://www.indeed.com/cmp/Abm-Industries,-Inc./reviews?start="+i);
		
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  Thread.sleep(5000);
		  wholeData=driver.findElements(By.xpath("//div[@class='cmp-Review-container']/div[2]/.."));
		  subject=driver.findElements(By.xpath("//div[@class='cmp-Review-container']/div[2]/div[1]"));
		  summary=driver.findElements(By.xpath("//div[@class='cmp-Review-container']/div[2]/div[3]/span/span[1]/span[1]"));
		  ratings=driver.findElements(By.xpath("//div[@class='cmp-Review-container']/div[1]/div[1]/div[1]"));
		  desig_Date_Location=driver.findElements(By.xpath("//div[@class='cmp-Review-container']/div[2]/div[2]/span"));
		  prosData=driver.findElements(By.xpath("//div[@class='cmp-Review-container']/div/div[4]/div/div[1]"));
		  consData=driver.findElements(By.xpath("//div[@class='cmp-Review-container']/div/div[4]/div/div[2]"));
		  totalProssCons=driver.findElements(By.xpath("//div[@class='cmp-Review-container']/div[2]/div[4]/div[1]"));
		  
			
			 for(WebElement contacts:prosData)
			   { 
			   // log.info(contacts.getText());
				 prosConsList.add(contacts.getText()+'\n');
			  }
	  }
	  String src="C:\\Users\\Prod\\Documents\\ABMdata.xlsx";
	  File file= new File(src);

	  FileInputStream fis= new FileInputStream(src);
	  XSSFWorkbook wb= new XSSFWorkbook();
	  XSSFSheet sh= wb.createSheet("abmdata");
	   int rowIndex=0;
		 for(String allContacts : prosConsList) {
			 Row row=sh.createRow(rowIndex++);
			 row.createCell(1).setCellValue(allContacts);
			 
			 //log.info(prosConsList);
			}
		 FileOutputStream fos= new FileOutputStream(file);
		   wb.write(fos);
		   wb.close();
	 }
	
    }

