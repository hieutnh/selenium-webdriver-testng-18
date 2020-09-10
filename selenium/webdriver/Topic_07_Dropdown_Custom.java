ackage webdriver;

import static org.testng.Assert.assertFalse;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dropdown_Custom {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\git\\selenium-webdriver-testng-18\\DriverChrome\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_JQUERY() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "5");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "10");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='10']")).isDisplayed());
		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "15");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());
		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "19");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());


	}
	
	@Test
	public void TC_02_Angular() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// HÃ m Ä‘Æ°á»£c Ä‘Ã¹ng nhiá»�u láº§n
	public void selectItemInDropDown(String parentLocator, String ItemLocator, String expectedItem) {
		// Click vÃ o 1 tháº» báº¥t kÃ¬
		driver.findElement(By.xpath(parentLocator)).click();
		//Chá»� cho táº¥t cáº£ item Ä‘Æ°á»£c xuáº¥t hiá»‡n
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ItemLocator)));
		//Láº¥y háº¿t táº¥t cáº£ cÃ¡c item Ä‘Æ°a vÃ o 1 list
		List <WebElement> allItems = driver.findElements(By.xpath(ItemLocator));
		//Tá»•ng sá»‘ lÆ°á»£ng item trong 1 dropdown
		System.out.println("Item size =" + allItems.size());
		//Duyá»‡t qua tá»«ng item
		for (WebElement item : allItems) {
		//Verify vá»›i text mÃ¬nh cáº§n click
	
			if(item.getText().equals(expectedItem)) {
				item.click();
				sleepInSecond(1);
				break;
			}
		}	
	}
	
	
	
	public String getHidenText(String csslocator) {
		return (String) jsExecutor.executeScript("document.querySelector(\"" + csslocator + "\").text");
	}
	
	public void sleepInSecond(long time) {
		try {Thread.sleep(time * 1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
