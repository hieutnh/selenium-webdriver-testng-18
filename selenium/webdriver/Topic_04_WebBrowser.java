package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_WebBrowser {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Verify_Url() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//a[@class='button']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

	}
	
	@Test
	public void TC_02_Verify_Title() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(By.xpath("//a[@class='button']")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_03_Navigate_Funtion() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@class='button']")).click();
		Assert.assertEquals(driver.getCurrentUrl (), "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl (), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	@Test
	public void TC_04_Get_Page_Source() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		driver.findElement(By.xpath("//a[@class='button']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

	}
	
	
	
	@AfterClass
		public void afterClass() {
			driver.quit();
	}
}
