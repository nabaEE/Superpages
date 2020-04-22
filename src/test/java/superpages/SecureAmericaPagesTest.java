package superpages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SecureAmericaPagesTest 
{
	protected  Logger log= LogManager.getLogger(SecureAmericaPagesTest.class);
	public  WebDriver driver;
	 @BeforeMethod()
	 public void openBrowser()
	 {
	 log.debug("-------Launching the browser--------");
	 System.setProperty("webdriver.gecko.driver", "C:\\\\Program Files\\\\gecko\\\\geckodriver.exe");
	// System.setProperty("webdriver.chrome.driver", "C:\\\\Program Files\\\\Chrome\\\\Chrome77\\\\chromedriver.exe");
	 //driver=new ChromeDriver();
	 driver=new FirefoxDriver();
	 driver.get("https://www.indeed.co.in/cmp/Securamerica/reviews?fcountry=ALL&start=460");
	 driver.manage().window().maximize();
	 }
	 @AfterMethod()
	 public void closeBrowser()
	 {
	 log.info("-------Closing the browser--------");
	 driver.close();
	 }
	 @Test()
	 public void printSecureAmericaPages() throws InterruptedException
	 {
	  String pagePath;
	  List<WebElement> data;
	  List<String> ContactList=new ArrayList<String>();
	  for(int i=460; i<=540; i=i+20) 
	  {
		  driver.get("https://www.indeed.co.in/cmp/Securamerica/reviews?fcountry=ALL&start="+i);
		
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  Thread.sleep(5000);
		  data=driver.findElements(By.xpath("//div[@id='cmp-content']/div"));
		  for(WebElement contacts:data )
		  { 
		  ContactList.add(contacts.getText());
		  log.info("      ");
		  }
		  
	  }
	//Print the stored list string
	  for(String allContacts: ContactList)
	  {  
		  log.info(ContactList);
	  }
	 }
  }
	

