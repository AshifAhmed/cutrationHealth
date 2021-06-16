package utility;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	public void enterDataIntoTextField(WebDriver driver, String locator, String attributeOfLocator, String fieldName, String value) {
		try {
			if(locator.equalsIgnoreCase("xpath")) {
				driver.findElement(By.xpath(attributeOfLocator)).sendKeys(value);
				System.out.println("Enter data into "+fieldName);
			}else if(locator.equalsIgnoreCase("id")) {
				driver.findElement(By.id(attributeOfLocator)).sendKeys(value);
				System.out.println("Enter data into "+fieldName);
			}else if(locator.equalsIgnoreCase("className")) {
				driver.findElement(By.id(attributeOfLocator)).sendKeys(value);
				System.out.println("Enter data into "+fieldName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnButton(WebDriver driver, String locator, String attributeOfLocator, String fieldName) {
		try {
			if(locator.equalsIgnoreCase("xpath")) {
				driver.findElement(By.xpath(attributeOfLocator)).click();
				System.out.println("Click on the "+fieldName+" Button");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getText(WebDriver driver, String locator, String attributeOfLocator, String fieldName) {
		String text = "";
		try {	
			if(locator.equalsIgnoreCase("xpath")) {
				text = driver.findElement(By.xpath(attributeOfLocator)).getText();
				System.out.println("Get Text of the "+fieldName);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	
	public void selectDropDown(WebDriver driver, String attributeOfLocator){
		try {
			WebElement element = driver.findElement(By.xpath(attributeOfLocator));
			Select dropdown = new Select(element);
			dropdown.selectByIndex(2);
			System.out.println("DropDown selected");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public List<WebElement> getElements(WebDriver driver, String attributeOfLocator){
		List<WebElement> elements = null;
		try {		
			waitUntilElementVisible(driver, attributeOfLocator);
			elements = driver.findElements(By.xpath(attributeOfLocator));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elements;
	}
	
	public WebElement getElement(WebElement element, String attributeOfLocator){
		WebElement ele = null;
		try {
			ele = element.findElement(By.xpath(attributeOfLocator));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ele;
	}
	
	public void waitUntilElementVisible(WebDriver driver, String attributeOfLocator) {
		WebDriverWait expectedWaits = new WebDriverWait(driver, 30);
		expectedWaits.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(attributeOfLocator)));
		
	}
}

