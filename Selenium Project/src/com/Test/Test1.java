package com.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.internal.TestResult;
import com.Class.Screenshot;
import com.Class.HomePage;
import com.Class.SignInPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

	public class Test1 {
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
		
		DateFormat df = new SimpleDateFormat("ddMMyy_HHmmss");
		Date dateobj = new Date();
		String FileName = df.format(dateobj);
		
		@Test
		public void InvalidEmail(){
			test = extent.createTest("Invalid Email Varification");
			objHomePage=new HomePage(driver);
			objHomePage.clickSignIn();
			test.log(Status.INFO, "Clicked on Sign In button successfully");
			objSignIn = new SignInPage(driver);
			objSignIn.Insertemail();
			test.log(Status.INFO, "Invalid Email inserted successfully");
			objSignIn.GetAttributeNewEmail();
			test.log(Status.INFO, "Email address field turned red successfully");
			}
		
				
		@BeforeTest	
		public void openthebrowser(){
			
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(ReportPath+"Test1_"+FileName+".html");
	    	extent = new ExtentReports();
	    	extent.attachReporter(htmlReporter);
			
	    	Properties prop = new Properties();
	    	InputStream input = null;
		try {
			input = new FileInputStream("C:\\Users\\Chris\\workspace\\Selenium Project\\Data.properties");
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
	   //     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.get(MainUrl);
	        
		}else if(brType.contains("firefox")){
			System.setProperty("webdriver.gecko.driver", GeckoPath);
	        driver = new FirefoxDriver();	
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.get(MainUrl);
	        
		}else if(brType.contains("ie")){
			System.setProperty("webdriver.ie.driver", IEPath);
	        driver = new InternetExplorerDriver();	
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.get(MainUrl);
	}
	}


		@AfterMethod
		public void ScreenCapture(ITestResult result){
			if(result.getStatus()==TestResult.FAILURE){
				Screenshot sc = new Screenshot();
				String imagePath = sc.CaptureScreen(driver, ImgPath+"Noman_"+FileName+".jpg");
		
				try {
				test.addScreenCaptureFromPath(imagePath);
				test.log(Status.FAIL, "Failed");
			}catch (IOException e) {
				e.printStackTrace();}
			
			}else{
				test.log(Status.PASS, "Passed");
				}}
			
		@AfterTest
		public void Result(){
			extent.flush();			
			driver.get(ReportPath+"Test1_"+FileName+".html");
			}
		}
