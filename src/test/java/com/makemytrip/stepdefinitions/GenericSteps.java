package com.makemytrip.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

//import org.assertj.core.api.AssertJProxySetup;
import com.makemytrip.pages.HomePage;
import com.makemytrip.webdriver.WebConnector;

public class GenericSteps {

	WebConnector con;
	HomePage hp;
	
	public GenericSteps(WebConnector con) {
		this.con = con;
	}
	
	@Before
	public void before(Scenario s) {
		System.out.println("***Bef*** "+s.getName());
//		con.initReports(s.getName());
	}
	
	@After
	public void after() {
		System.out.println("***Aft***");	
//		con.quit();
	}
	
	
	@Given("^I open (.*)$")
	public void openBrowser(String browserName) {		
		//System.out.println("Opening Browser "+con.name);
//		con.infoLog("Opening Browser "+browserName);
		con.openBrowser(browserName);
	}
	
	@And("^I go to (.*)$")
	public void navigate(String url) {
		//System.out.println("Navigating to "+ con.name);
//		con.infoLog("Navigating to "+ url);
		con.navigate(url);
	}

	@And("^I click on Flights link$")
	public void click() {
//		con.infoLog("Clicking on "+ locatorKey);
		hp = new HomePage(con.driver);
		hp.clickFlightsLink();
	}
	
	@When("^I accept alert$")
	public void iAcceptAlert() {
		con.acceptAlertIfPresent();
	}
	
}
