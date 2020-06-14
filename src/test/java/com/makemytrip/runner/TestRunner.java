package com.makemytrip.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;




@CucumberOptions(
		dryRun=false,
		strict=true,
		monochrome=false,
		features= {"src/test/resources/"},
		glue = {"com.makemytrip.stepdefinitions"},
		plugin= {
				"html:target/site/cucumber-html",
				"json:target/cucumber1.json"}
//		tags= {""}
		)
public class TestRunner extends AbstractTestNGCucumberTests {

	@DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }	
}
