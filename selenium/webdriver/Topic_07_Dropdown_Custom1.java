package webdriver;

import static org.testng.Assert.assertFalse;

import java.awt.RenderingHints.Key;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.internal.compiler.ast.Argument;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.ClearElement;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.remote.server.handler.internal.ArgumentConverter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dropdown_Custom1 {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hieut\\git\\selenium-webdriver-testng-18\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	
	public void TC_01_JQUERY() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "5");
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'5')]")).isDisplayed());
		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "10");
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'10')]")).isDisplayed());
		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "15");
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'15')]")).isDisplayed());
		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'19')]")).isDisplayed());


	}
	
	
	public void TC_02_Angular() {
		driver.get("https://bom.to/GjUg3AR");
		selectItemInDropDown("//ejs-dropdownlist[@id='games']//span[contains(@class, 'e-input-group')]", "//ul[@id='games_options']/li", "American Football");
		Assert.assertEquals(getHidenText("select[id='games_hidden'] option"), "American Football");
		selectItemInDropDown("//ejs-dropdownlist[@id='games']//span[contains(@class, 'e-input-group')]", "//ul[@id='games_options']/li", "Golf");
		Assert.assertEquals(getHidenText("select[id='games_hidden'] option"), "Golf");
	}

	@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInDropDown("//div[@role='listbox']", "//div[@role='option']", "Jenny Hess");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Jenny Hess']")).isDisplayed());
		selectItemInDropDown("//div[@role='listbox']", "//div[@role='option']", "Christian");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Christian']")).isDisplayed());

	}
	
	@Test
	public void TC_04_vueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInDropDown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li", "First Option");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Jenny Hess']")).isDisplayed());
		selectItemInDropDown("//div[@role='listbox']", "//div[@role='option']", "Christian");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Christian']")).isDisplayed());
	}
	@Test
	public void TC_05_editAble() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		sendkeydropdown("//div[@id='default-place']//input[@class='form-control es-input']", "Audi");
	
		Assert.assertEquals(getHidenText("#default-place li.es-visible"), "Audi");
		selectItemInDropDown("//div[@role='listbox']", "//div[@role='option']", "Christian");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Christian']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	//Hàm cho sendkey
	public void sendkeydropdown(String locator, String value) {
		driver.findElement(By.xpath(locator)).sendKeys(value);
		sleepInSecond(1);
		driver.findElement(By.xpath(locator)).clear();
		sleepInSecond(1);	
		driver.findElement(By.xpath(locator)).sendKeys(Keys.TAB);
		sleepInSecond(1);
	}
		
	
	
	// Hàm được đùng nhiều lần
	public void selectItemInDropDown(String parentLocator, String ItemLocator, String expectedItem) {
		// Click vào 1 thẻ bất kì
		driver.findElement(By.xpath(parentLocator)).click();
		//Chờ cho tất cả item được xuất hiện
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ItemLocator)));
		//Lấy hết tất cả các item đưa vào 1 list
		List <WebElement> allItems = driver.findElements(By.xpath(ItemLocator));
		//Tổng số lượng item trong 1 dropdown
		System.out.println("Item size =" + allItems.size());
		//Duyệt qua từng item
		for (WebElement item : allItems) {
		//Verify với text mình cần click
			
			if(item.getText().equals(expectedItem)) {
				// Scroll truowcs khi click
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				sleepInSecond(1);
				break;
			}
		}	
	}
	
	public String getHidenText(String csslocator) {
		return (String) jsExecutor.executeScript("return document.querySelector(\"" + csslocator + "\").text");
	}
	

	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
