package utility;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import pages.SaucedemoPage;

public abstract class DriverClass {
	public static WebDriver driver = null;
	public static String homePage = "https://www.saucedemo.com";
	
	@BeforeClass(alwaysRun=true)
	@Parameters("browser")
	public void setUp(@Optional("chrome")String browser) {

		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", new File("libs").getAbsolutePath()+"/chromedriver");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
		}else if(browser.contains("headlesschrome")) {
			System.setProperty("webdriver.chrome.driver", new File("libs").getAbsolutePath()+"/chromedriver");
			ChromeOptions options = new ChromeOptions();  
			options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");  
			driver = new ChromeDriver(options); 
		}
		
		navigateToLandingPage();
	}

	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		try {
			driver.close();
		} catch (Exception e) {			
			driver = null;
		}
	}

	public void navigateToLandingPage() {
		driver.navigate().to(homePage);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public SaucedemoPage saucedemoPage() {

		SaucedemoPage landingScreen = new SaucedemoPage(driver);
		return landingScreen;

	}
}
