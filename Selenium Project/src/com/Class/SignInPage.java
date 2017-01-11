package com.Class;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Xls_Reader;


public class SignInPage {
	WebDriver driver;
	
	@FindBy(xpath=".//*[@id='email_create']")
	WebElement NewEmail;
	
	@FindBy(xpath=".//*[@id='SubmitCreate']")
	WebElement createaccbutton;
	

	
	public SignInPage(WebDriver driver){
		this.driver = driver;
	PageFactory.initElements(driver, this);
	}
	
	public void Insertemail(){
		Xls_Reader xls = new Xls_Reader("C:\\Users\\Chris\\workspace\\Selenium Project\\Test Data.xlsx");
		String Invalidemail=xls.getCellData("Sheet1", 2, 2);
		NewEmail.sendKeys(Invalidemail);
		createaccbutton.click();
		}
	
	public void GetAttributeNewEmail(){
		String FontColor=(NewEmail.getCssValue("color"));
			Assert.assertTrue(FontColor.contains("241"));
		}
	}

	

