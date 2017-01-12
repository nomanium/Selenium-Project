package com.Class;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


	public class Browser {
		WebDriver driver;
		SignInPage objSignIn;
		HomePage objHomePage;
		ExtentReports extent;
		ExtentTest test;
		String brType;
		String MainUrl;
		String PropFileLoc;
		String ChromePath;
		String GeckoPath;
		String IEPath;
		String ImgPath;
		String ReportPath;
		
		DateFormat df = new SimpleDateFormat("MMddyy_HHmmss");
		Date dateobj = new Date();
		String FileName = df.format(dateobj);
		
		
	//	By sbutton=By.xpath(".//*[@id='header']/div[2]/div/div/nav/div[1]/a");
	
	public Browser(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@BeforeTest	
	public void openthebrowser(){
				
    	Properties prop = new Properties();
    	InputStream input = null;
	try {
		input = new FileInputStream(System.getProperty("user.dir")+ "\\Data.properties");
	}catch (FileNotFoundException e) {
		
		e.printStackTrace();}
	try {
		prop.load(input);
	}catch (IOException e) {
		
		e.printStackTrace();
		}
		ChromePath=prop.getProperty("ChromeDriverPath");
		GeckoPath=prop.getProperty("GeckoDriverPath");
		IEPath=prop.getProperty("IEDriverPath");
		MainUrl=prop.getProperty("MainUrl");
		brType=prop.getProperty("browserType");
		ImgPath=prop.getProperty("ScreenShotPath");
		ReportPath=prop.getProperty("ReportPath");
	
	if(brType.contains("chrome")){
		System.setProperty("webdriver.chrome.driver", ChromePath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(MainUrl);
        
	}else if(brType.contains("firefox")){
		System.setProperty("webdriver.gecko.driver", GeckoPath);
        driver = new FirefoxDriver();	
        driver.manage().window().maximize();
        driver.get(MainUrl);
        
	}else if(brType.contains("ie")){
		System.setProperty("webdriver.ie.driver", IEPath);
        driver = new InternetExplorerDriver();	
        driver.manage().window().maximize();
        driver.get(MainUrl);
}
	}}
