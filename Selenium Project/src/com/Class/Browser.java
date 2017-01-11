package com.Class;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;


	public class Browser {
		WebDriver driver;
		String brType;
		String MainUrl;
		
	//	By sbutton=By.xpath(".//*[@id='header']/div[2]/div/div/nav/div[1]/a");
	
	public Browser(WebDriver driver){
		this.driver = driver;
	//	PageFactory.initElements(driver, this);
	}
	
	public void openthebrowser(){
		Properties prop = new Properties();
		InputStream input = null;
	
		try {
			input = new FileInputStream("C:\\Users\\Chris\\workspace\\Selenium Project\\Data.properties");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			prop.load(input);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		MainUrl=prop.getProperty("MainUrl");
		brType=prop.getProperty("browserType");

	
    	if(brType.contains("chrome")){
    	    System.setProperty("webdriver.chrome.driver","C:\\Users\\Chris\\workspace\\Selenium Project\\Driver\\chromedriver.exe");
   	        driver = new ChromeDriver();
   	     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(MainUrl);
    	}else if(brType.contains("firefox")){
    		System.setProperty("webdriver.gecko.driver","C:\\Users\\Chris\\workspace\\Selenium Project\\Driver\\geckodriver.exe");
   	        driver = new FirefoxDriver();	
   	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(MainUrl);
    	}else if(brType.contains("ie")){
    		System.setProperty("webdriver.ie.driver","C:\\Users\\Chris\\workspace\\Selenium Project\\Driver\\IEDriverServer.exe");
   	        driver = new InternetExplorerDriver();	
   	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(MainUrl);
    	}
    	}
	}

