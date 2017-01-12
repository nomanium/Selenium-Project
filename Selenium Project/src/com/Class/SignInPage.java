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
	@FindBy(xpath=".//*[@id='create_account_error']")
	WebElement createaccerror;
	
	
	public SignInPage(WebDriver driver){
		this.driver = driver;
	PageFactory.initElements(driver, this);
	}
	
	public void Insertemail(){
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+ "\\Test Data.xlsx");
		String Invalidemail=xls.getCellData("Sheet1", 1, 2);
		NewEmail.sendKeys(Invalidemail);
		createaccbutton.click();
		}
	
	public void Insertregisteredemail(){
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+ "\\Test Data.xlsx");
		String registeredlidemail=xls.getCellData("Sheet1", 1, 3);
		NewEmail.sendKeys(registeredlidemail);
		createaccbutton.click();
		}
	
	public void GetAttributeNewEmail(){
		String FontColor=(NewEmail.getCssValue("color"));
			Assert.assertTrue(FontColor.contains("241"));
		}
	public void geterrormessage(){
		String errorMsg=createaccerror.getText();
		Assert.assertEquals(errorMsg, "An account using this email address has already been registered. Please enter a valid password or request a new one.");
			
		}
	}

	

