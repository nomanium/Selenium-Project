import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Browser {
	ExtentReports extent;
	ExtentTest test;
	WebDriver driver;
	String browserType;
	String MainUrl;
	Properties prop = new Properties();
	InputStream input = null;{
	
		try {
			input = new FileInputStream("C:\\Users\\Chris\\workspace\\Selenium Project\\Data.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		prop.getProperty("MainUrl");
		prop.getProperty("browserType");
	}
	public void openthebrowser(){
		
    	if(browserType.contains("chrome")){
    	    System.setProperty("webdriver.chrome.driver","C:\\Users\\Chris\\workspace\\Selenium Project\\Driver\\chromedriver.exe");
   	        driver = new ChromeDriver();	
   	        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
   	        test.log(Status.INFO, "opening chrome browser");
            driver.get(MainUrl);
    	}else if(browserType.contains("firefox")){
    		System.setProperty("webdriver.gecko.driver","C:\\Users\\Chris\\workspace\\Selenium Project\\Driver\\geckodriver.exe");
   	        driver = new FirefoxDriver();	
   	        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
   	     test.log(Status.INFO, "opening firefox browser");
            driver.get(MainUrl);
    	}else if(browserType.contains("ie")){
    		System.setProperty("webdriver.ie.driver","C:\\Users\\Chris\\workspace\\Selenium Project\\Driver\\IEDriverServer.exe");
   	        driver = new InternetExplorerDriver();	
   	        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
   	     test.log(Status.INFO, "opening edge browser");
            driver.get(MainUrl);
    	}
	}
	public Browser(WebDriver argdriver){
		this.driver=argdriver;
	}
	
}
