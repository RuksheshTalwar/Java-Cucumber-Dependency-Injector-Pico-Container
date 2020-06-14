package com.makemytrip.webdriver;

//import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.qtpselenium.reports.ExtentManager;
public class WebConnector {
	
	public WebDriver driver;
	public String name;
	public Properties prop;
//	public ExtentReports rep;
//	public ExtentTest scenario;
	
	public WebConnector() {
		if(prop==null) {
			
			try {
				prop= new Properties();
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\project.properties");
				prop.load(fs);
			} catch (Exception e) {
				e.printStackTrace();
				// report
			}
		}
	}
	
	
	public void openBrowser(String browserName) {
//		if(prop.getProperty("gridRun").equals("Y")) {
			// invoke browser through grid
//			DesiredCapabilities cap=null;
//			if(browserName.equals("Mozilla")) {
//				cap = DesiredCapabilities.firefox();
//				cap.setJavascriptEnabled(true);
//				cap.setPlatform(Platform.WINDOWS);
//			}else if(browserName.equals("Chrome")) {
//				cap = DesiredCapabilities.chrome();
//				cap.setJavascriptEnabled(true);
//				cap.setPlatform(Platform.WINDOWS);
//			}
			
//			try {
//				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
//		}else {
			if(browserName.equals("Mozilla")){
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver.exe");
				driver= new FirefoxDriver();
			}
			else if(browserName.equals("Chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
				driver= new ChromeDriver();
			}
			else if(browserName.equals("IE")){
				driver= new InternetExplorerDriver();
			}
//		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		infoLog("Opened Browser");
	}

	public void navigate(String url) {
		//System.out.println(prop.getProperty(urlKey));
		driver.get(url);
	}

	// true - present
	// false - not present
	public boolean isElementPresent(String objectKey) {
		List<WebElement> e=null;
		
		if(objectKey.endsWith("_xpath")) {
			e = driver.findElements(By.xpath(prop.getProperty(objectKey)));// present
		}else if(objectKey.endsWith("_id")) {
				e = driver.findElements(By.id(prop.getProperty(objectKey)));// present
		}
		else if(objectKey.endsWith("_name")) {
			e = driver.findElements(By.name(prop.getProperty(objectKey)));// present
		}
		else if(objectKey.endsWith("_css")) {
			e = driver.findElements(By.cssSelector(prop.getProperty(objectKey)));// present
		}
		if(e.size()==0)
			return false;
		else
			return true;
	}
	
	public void acceptAlertIfPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}catch(Exception e) {
			// not present
		}
	}
	
	/**********logging**************/
//	public void infoLog(String msg) {
//		scenario.log(Status.INFO, msg);
//	}
	
//	public void reportFailure(String errMsg) {
//		// fail in extent reports
//		scenario.log(Status.FAIL, errMsg);
//		takeSceenShot();
//		// take screenshot and put in repots
//		// fail in cucumber as well
//		assertThat(false);
//	}
	
//	public void takeSceenShot(){
//		// fileName of the screenshot
//		Date d=new Date();
//		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
//		// take screenshot
//		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		try {
//			// get the dynamic folder name
//			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
//			//put screenshot file in reports
//			scenario.log(Status.FAIL,"Screenshot-> "+ scenario.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//
//	/**************Reporting******************/
//	public void quit() {
//		if(rep!=null)
//			rep.flush();
//		if(driver !=null)
//			driver.quit();
//	}
//	public void initReports(String scenarioName) {
//		rep = ExtentManager.getInstance(prop.getProperty("reportPath"));
//		scenario = rep.createTest(scenarioName);
//		scenario.log(Status.INFO, "Starting " +scenarioName);
//	}


}
