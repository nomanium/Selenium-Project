package com.Class;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MyAccountPage {
		WebDriver driver;
		//defining xpath according to page factory model
		@FindBy(xpath=".//*[@id='header']/div[2]/div/div/nav/div[1]/a")
		WebElement AccName;
		@FindBy(xpath=".//*[@id='center_column']/div/div[1]/ul/li[5]/a/span")
		WebElement MyPersonalInfoButton;
		@FindBy(xpath=".//*[@id='lastname']")
		WebElement LastName;
		@FindBy(xpath=".//*[@id='old_passwd']")
		WebElement CurrentPassword;
		@FindBy(xpath=".//*[@id='center_column']/div/form/fieldset/div[11]/button")
		WebElement SaveButton;
		@FindBy(xpath=".//*[@id='header']/div[2]/div/div/nav/div[1]/a/span")
		WebElement Account;
		
		
		DateFormat df = new SimpleDateFormat("HH");
		Date dateobj = new Date();
		String FileName = df.format(dateobj);
		
		String alphabet= "abcdefghijklmnopqrstuvwxyz";
	    String s = "";
	    Random random = new Random();
	    int randomLen = 2+random.nextInt(3); 			//creating 2 or 3 random letters to add after names
	    {for (int i = 0; i < randomLen; i++) {
	        char c = alphabet.charAt(random.nextInt(26));
	        s+=c;
	    }}

		
		public MyAccountPage(WebDriver driver){		//constructor
			this.driver=driver;
			PageFactory.initElements(driver, this);			
		}
		
		//comparing first name and last name according to provided data
		public void VarifyAccName(){
			String AccountName = AccName.getText();
			Assert.assertFalse(AccountName.contains( "Abdullah"+s+" "+"Noman"+s)); 	
		}
		
		public void LastNameUpdate(){
			MyPersonalInfoButton.click();
			LastName.clear();								//clearing existing data
			LastName.sendKeys("Mamun"+s);					//putting new last name
			CurrentPassword.sendKeys("Nomanium"+FileName);
			SaveButton.click();
		}
		
		public void LastNameVarification(){
			String AccountName = AccName.getText();
			Assert.assertFalse(AccountName.contains( "Abdullah"+s+" "+"Mamun"+s)); //Comparing last name with inserted last name		
		
		}
}
