package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_Xpath {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		String emailerrormessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(emailerrormessage,"This is a required field.");
		String passworderrormessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(passworderrormessage, "This is a required field.");
	
	}
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("12332.@213123.2131");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		String emailerrormessage = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(emailerrormessage, "Please enter a valid email address. For example johndoe@domain.com.");
	
	}
	@Test
	public void TC_03_LoginWithInvalidPasswor() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("hieutnh209@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		String passworderrormessage = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passworderrormessage,"Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void TC_04_LoginWithIcorrectPassword() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("hieutnh209@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		String errorpassword = driver.findElement(By.xpath("//ul[@class='messages']//span")).getText();
		Assert.assertEquals(errorpassword,"Invalid login or password.");
	
	}
	@Test
	public void TC_05_LoginWithValidEmailPassword() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123");
		driver.findElement(By.id("send2")).click();
		String MyDashboard = driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText();
		Assert.assertEquals(MyDashboard,"MY DASHBOARD");
		
		String HelloAuto = driver.findElement(By.xpath("//p[@class='hello']/strong")).getText();
		Assert.assertEquals(HelloAuto,"Hello, Automation Testing!");
		
		String Name = driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'Automation Testing')]")).getText();
		Assert.assertEquals(Name,"Automation Testing");
		
		String email = driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'automation_13@gmail.com')]")).getText();
		Assert.assertEquals(email,"automation_13@gmail.com");
		
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
	}
	
	@Test
	public void TC_06_CreateAnAcount() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@class='button']")).click();
		driver.findElement(By.id("//input[@id='firstname']")).sendKeys("Tran");
		
		driver.findElement(By.id("//input[@id='lastname']")).sendKeys("Hieu");
		
		driver.findElement(By.id("//input[@id='email_address']")).sendKeys("hieutnh" + randomnumber() + "@gmail.com");
		
		driver.findElement(By.id("//input[@id='password']")).sendKeys("123123");
		
		driver.findElement(By.id("//input[@id='confirmation']")).sendKeys("123123");
		
		driver.findElement(By.xpath("//button[@class='Register']")).click();
		
		String Confirmregister2 = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
		Assert.assertEquals(Confirmregister2,"Thank you for registering with Main Website Store.");
	
		String MyDashboard2 = driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText();
		Assert.assertEquals(MyDashboard2,"My Dashboard");
		
		String HelloAuto2 = driver.findElement(By.xpath("//p[@class='hello']/strong")).getText();
		Assert.assertEquals(HelloAuto2,"Hello, Tran Hieu!");
		
		String Name2 = driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'Tran Hieu')]")).getText();
		Assert.assertEquals(Name2,"Tran Hieu");
		
		String email2 = driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'hieutnh1@gmail.com')]")).getText();
		Assert.assertEquals(email2,"hieutnh1@gmail.com");
		
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	}
	
	public int randomnumber() {
		Random rand = new Random();
			return rand.nextInt(999999);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
