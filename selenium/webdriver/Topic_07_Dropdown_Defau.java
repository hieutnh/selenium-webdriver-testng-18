package webdriver;

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

public class Topic_07_Dropdown_Defau {
	WebDriver driver;
	Select select;

	String email, pass;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\hieut\\git\\selenium-webdriver-testng-18\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Khai bao bien
		email = "testthoima" + getRanDom() + "@gmail.com";
		pass = "123456";
	}

	@Test
	public void TC_01_HTML_Dropdown() {

		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.xpath("//input[@id='gender-male']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("test");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("thoima");
		// select day
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText("1");
		Assert.assertEquals(select.getOptions().size(), 32);

		// select month
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText("May");
		Assert.assertEquals(select.getOptions().size(), 13);

		// select year
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText("1980");
		Assert.assertEquals(select.getOptions().size(), 112);

		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@id='register-button']")).click();

		// Verify register
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");

		driver.findElement(By.xpath("//a[@class='ico-account']")).click();

		// select day verify
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");
		
		// select month verify
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
		
		// select year verify
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1980");

	}

	public int getRanDom() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
