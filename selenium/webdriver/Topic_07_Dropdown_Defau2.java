package webdriver;

import static org.testng.Assert.assertFalse;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dropdown_Defau2 {
	WebDriver driver;
	Select select;

	String dropdown, pass;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\git\\selenium-webdriver-testng-18\\DriverChrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		

	
	}

	@Test
	public void TC_01_HTML_Dropdown() throws InterruptedException {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		Assert.assertFalse(select.isMultiple());
		//get bằng visible
		select.selectByVisibleText("Manual Testing");
		Thread.sleep(4000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");	
		//get bằng value
		select.selectByValue("desktop");
		Thread.sleep(4000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Desktop Testing");	
		//get bằng index
		select.selectByIndex(9);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
		Thread.sleep(4000);
		Assert.assertEquals(select.getOptions().size(), 10);

	}
	


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
