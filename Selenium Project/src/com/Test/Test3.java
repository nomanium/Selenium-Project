package com.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Class.Browser;
import com.Class.CreateAccountPage;
import com.Class.HomePage;
import com.Class.MyAccountPage;
import com.Class.Screenshot;
import com.Class.SignInPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Test3 {
	WebDriver driver;
	Browser objbrowser;
	SignInPage objSignIn;
	HomePage objHomePage;
	CreateAccountPage objCreateAccountPage;
	MyAccountPage objMyAccountPage;
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
public void CreateAccount() throws InterruptedException{
	test = extent.createTest("Create new account with valid new Email");
	objHomePage=new HomePage(driver);
	objHomePage.clickSignIn();		//Clicking sign in button
	test.log(Status.INFO, "Clicked on Sign In button successfully");
	objSignIn = new SignInPage(driver);
	objSignIn.insertnewemail();		//running method from SignInPage class, inserting new, unused and random email
	test.log(Status.INFO, "New account creation page displayed successfully");
	Thread.sleep(3000);
	objCreateAccountPage=new CreateAccountPage(driver);
	objCreateAccountPage.Register();
	test.log(Status.INFO, "Inserted data successfully");
	objCreateAccountPage.confirmation();
	test.log(Status.INFO, "New account creation message displayed successfully");
	objMyAccountPage=new MyAccountPage(driver);
	objMyAccountPage.VarifyAccName();
	test.log(Status.INFO, "Account name varyfied successfully");
	objMyAccountPage.LastNameUpdate();
	test.log(Status.INFO, "Last Name updated successfully");
	objMyAccountPage.LastNameVarification();
	test.log(Status.INFO, "Updated Last Name varified successfully");
	Thread.sleep(5000);
}
	

@BeforeTest
public void openbrowser(){
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "\\Extent Reports\\Test3_"+FileName+".html");
	extent = new ExtentReports();
	extent.attachReporter(htmlReporter); //Creating and attaching html report to extent report
	
	Properties prop = new Properties(); //creating properties object
	InputStream input = null;
try {
	input = new FileInputStream(System.getProperty("user.dir")+ "\\Data.properties");
}catch (FileNotFoundException e) {
	
	e.printStackTrace();}
try {
	prop.load(input);		//loading properties file
}catch (IOException e) {
	
	e.printStackTrace();
	}
//Importing strings and links from properties file
	ChromePath=prop.getProperty("ChromeDriverPath");
	GeckoPath=prop.getProperty("GeckoDriverPath");
	IEPath=prop.getProperty("IEDriverPath");
	MainUrl=prop.getProperty("MainUrl");
	brType=prop.getProperty("browserType");
	ImgPath=prop.getProperty("ScreenShotPath");
	ReportPath=prop.getProperty("ReportPath");

if(brType.contains("chrome")){	//Navigating to eBFS homepage by chrome browser
	System.setProperty("webdriver.chrome.driver", ChromePath);
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get(MainUrl);
    
}else if(brType.contains("firefox")){		//Navigating to eBFS homepage by Firefox browser
	System.setProperty("webdriver.gecko.driver", GeckoPath);
    driver = new FirefoxDriver();	
    driver.manage().window().maximize();
    driver.get(MainUrl);
    
}else if(brType.contains("ie")){		//Navigating to eBFS homepage by IE browser
	System.setProperty("webdriver.ie.driver", IEPath);
    driver = new InternetExplorerDriver();	
    driver.manage().window().maximize();
    driver.get(MainUrl);
}}

@AfterMethod	//Captures Screenshot if result fails and attach to extent report.
public void ScreenCapture(ITestResult result) throws IOException{
	if(result.getStatus()==ITestResult.FAILURE){
		String imagePath = Screenshot.CaptureScreen(driver,"Test3_"+FileName);
		test.addScreenCaptureFromPath(imagePath);
		test.log(Status.FAIL, "Failed. Screenshot attached below:");
	}}
	
@AfterTest
public void Result(){
	extent.flush();		//Ending extent report and Writing into file 	
	driver.get(System.getProperty("user.dir")+ "\\Extent Reports\\Test3_"+FileName+".html"); //openning report
	}
}


