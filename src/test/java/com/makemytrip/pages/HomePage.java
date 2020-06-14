package com.makemytrip.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {
	
	WebDriver driver;

	@FindBy(css = ".chNavIcon.appendBottom2.chSprite.chFlights.active")
	public WebElement flightsLink;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickFlightsLink(){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		flightsLink.click();
	}
	
	
}
