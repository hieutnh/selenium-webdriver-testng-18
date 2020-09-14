package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_interactions {
	WebDriver driver;
	WebElement element;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	
	public void TC_01_Hover_Mouse() {
		driver.get("http://www.myntra.com/");
		element = driver.findElement(By.xpath("//a[@class='desktop-main'][contains(text(),'Kids')]"));
		action.moveToElement(element).perform();
		driver.findElement(By.xpath("//a[contains(text(),'Home & Bath')]")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(),
				"Kids Home Bath");

	}

	
	public void TC_02_Hover_Mouse() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> allItem = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.clickAndHold(allItem.get(0)).moveToElement(allItem.get(3)).release().perform();
		List<WebElement> allItemSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(allItemSelected.size(), 4);

		for (WebElement displayitemSelected : allItemSelected) {
			System.out.println(displayitemSelected.getText());
		}

	}

	@Test
	public void TC_03_Ctrl_And_Click() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> allItem = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		//Nhấn button Ctrl
		action.keyDown(Keys.CONTROL).perform();
		//Click chuột trái
		action.click(allItem.get(0)).click(allItem.get(2)).click(allItem.get(5)).click(allItem.get(10)).perform();
		//Thả button Ctrl
		action.keyUp(Keys.CONTROL).perform();
		List <WebElement> allItemSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(allItemSelected.size(), 4);
		
		for (WebElement displayitemSelected : allItemSelected) {
			System.out.println(displayitemSelected.getText());
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
