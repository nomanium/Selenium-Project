package com.Class;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	public static String CaptureScreen(WebDriver driver, String ImageName){
		
		String ImgPath;
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
		ImgPath=prop.getProperty("ScreenShotPath");
		
		DateFormat df = new SimpleDateFormat("ddMMyy_HHmmss");
		Date dateobj = new Date();
		String FileName = df.format(dateobj);
		
		TakesScreenshot Scn = (TakesScreenshot) driver;
		File srcFile = Scn.getScreenshotAs(OutputType.FILE);
		String dest =(ImgPath+"Noman_"+FileName+".jpg");
		File destFile = new File(dest);
		try{
			FileUtils.copyFile(srcFile, destFile);
		}catch(IOException e){
			e.getStackTrace();
		}
		return dest+ ".jpg";
		
		
	}

}
