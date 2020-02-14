package com.unimoni.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	private WebDriver driver;

	private String browser;

	private String baseUrl;

	@BeforeSuite
	@Parameters({ "browser", "baseUrl" })
	public void readXml(String browser, String baseUrl) {

		this.browser = browser;

		this.baseUrl = baseUrl;

	}

	@BeforeTest
	public void intialiseDriver() {

		if (browser.equalsIgnoreCase("chrome")) {

		/*	System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\sagar.mv\\Eclipse2\\AutomationFramework\\src\\main\\resources\\drivers\\chromedriver.exe");*/
			
			WebDriverManager.chromedriver().setup();
			
			driver = new ChromeDriver();

			/*DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome();
			handlSSLErr.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new ChromeDriver(handlSSLErr);
			
			
*/
			DriverManager.setWebDriver(driver);

			DriverManager.getDriver().manage().window().maximize();

			DriverManager.getDriver().get(baseUrl);

		}

	}

}
