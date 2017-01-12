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
		
		DateFormat df = new SimpleDateFormat("MMddyy_HHmmss");
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
			
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "\\Extent Reports\\Test1_"+FileName+".html");
	    	extent = new ExtentReports();
	    	extent.attachReporter(htmlReporter);
			
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
	}


		@AfterMethod	//Captures Screenshot if result fails and attach to extent report.
		public void ScreenCapture(ITestResult result) throws IOException{
			if(result.getStatus()==ITestResult.FAILURE){
				String imagePath = Screenshot.CaptureScreen(driver,"Test1_"+FileName);
				test.addScreenCaptureFromPath(imagePath);
				test.log(Status.FAIL, "Failed. Color did not match as expected. Screenshot attached below:");
			}}
			
		@AfterTest
		public void Result(){
			extent.flush();			
			driver.get(System.getProperty("user.dir")+ "\\Extent Reports\\Test1_"+FileName+".html");
			}
		}
