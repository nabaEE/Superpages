package superpages;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SuperpagesScraptest
{
 protected static Logger log= LogManager.getLogger(SuperpagesScraptest.class);
public static WebDriver driver;
 @BeforeMethod()
 public static void openBrowser()
 {
 log.debug("-------Launching the browser--------");
 System.out.println("--------Launching the browser----------");
 System.setProperty("webdriver.gecko.driver", "C:\\\\Program Files\\\\gecko\\\\geckodriver.exe");
// System.setProperty("webdriver.chrome.driver", "C:\\\\Program Files\\\\Chrome\\\\Chrome77\\\\chromedriver.exe");
 //driver=new ChromeDriver();
 driver=new FirefoxDriver();
 driver.get("https://www.superpages.com");
 driver.manage().window().maximize();
 }
 @AfterMethod()
 public static void closeBrowser()
 {
 log.info("-------Closing the browser--------");
 driver.close();
 }
 @Test()
 public static void printClinicContactDetails() throws InterruptedException
 {
 WebElement searchbox=driver.findElement(By.xpath("//form[@id='navbar-form']/suggest/div/input"));
 WebElement searchButton=driver.findElement(By.id("srch-btn"));
 List<WebElement> clinics;
 List<String> clinicData=new ArrayList<String>();
 //Enter clinic near me in the searchbox then click on search
 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 searchbox.sendKeys("clinic");
 searchButton.click();
 String pageXpath;
 String str=driver.findElement(By.xpath("//*[@id=\"listings\"]/div/div[2]/div[2]/div[1]/div/div[1]")).getText();
  for(int i=1; i<=5; i++)
  {
	 if(i>1)
	 {
		 try 
		 {
	 pageXpath="//div[@class='pull-right pagebuttons']/a[text()='"+i+"']";
	 driver.findElement(By.xpath(pageXpath)).click();
	 Thread.sleep(2000);
         }
	 catch(StaleElementReferenceException e)
	    {
		 pageXpath="//div[@class='pull-right pagebuttons']/a[text()='"+i+"']";
		 driver.findElement(By.xpath(pageXpath)).click();
		 Thread.sleep(2000);
	    }
	 }
     
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 Thread.sleep(5000);
	 //Capture the data from the page and store in the list string
	 clinics=driver.findElements(By.xpath("//div[@id='listings']/div/div[2]/div[2]/div/div/div[1]"));
	for(WebElement contacts:clinics )
	{
	 clinicData.add(contacts.getText());
	 log.info("      ");
	}
   
  }
 //Print the stored list string
  for(String allContacts: clinicData)
  {  
	  log.info(clinicData);
  }
 }
}
