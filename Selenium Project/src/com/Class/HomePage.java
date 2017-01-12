package com.Class;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
		WebDriver driver;
		//defining xpath according to page factory model
		@FindBy(xpath=".//*[@id='header']/div[2]/div/div/nav/div[1]/a")
		WebElement signInButton;
	
	public HomePage(WebDriver driver){		//Constructor
		this.driver = driver;
	PageFactory.initElements(driver, this);
	}
	public void clickSignIn(){
		signInButton.click();		//Clicking sign in button
	}

	}

