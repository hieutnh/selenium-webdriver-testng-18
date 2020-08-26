package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_02_xpath {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_locator() throws InterruptedException {
		// Mở ra 1 trang web
		driver.get("http://live.demoguru99.com/index.php/");
		//id
		driver.findElement(By.id("search")).sendKeys("LG");
		Thread.sleep(3000);
		
		driver.findElement(By.className("search-button")).click();
		
		//Class
		driver.findElement(By.className("btn-cart")).click();
		Thread.sleep(3000);
		
		//link text
		driver.findElement(By.linkText("PRIVACY POLICY")).click();
		Thread.sleep(3000);
		
		//link parktial link text
		driver.findElement(By.partialLinkText("ADVANCED")).click();
		Thread.sleep(3000);
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
