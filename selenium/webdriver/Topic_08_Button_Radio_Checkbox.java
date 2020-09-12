package webdriver;

import static org.testng.Assert.assertTrue;

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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();


	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/");
		WebElement loginbutton = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
		//verify loginbutton disable
		Assert.assertFalse(loginbutton.isEnabled());
		System.out.println("Login Status = " + loginbutton.isEnabled());
		
		
		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("testthoi@gmail.com");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456");
		sleepInSecond(2);
		//verify loginbutton enable
		loginbutton = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
		Assert.assertTrue(loginbutton.isEnabled());
		System.out.println("Login Status = " + loginbutton.isEnabled());
		
		 //verify sai email/sdt
		driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).click();
		sleepInSecond(2);
		String errormessage = driver.findElement(By.xpath("//div[@class='fhs-popup-msg fhs-login-msg']")).getText();
		Assert.assertEquals(errormessage, "Số điện thoại/Email hoặc Mật khẩu sai!");
		
		driver.navigate().refresh();
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login active']")).click();
		loginbutton = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
		//remove attribute disable loginbutton
		removeAttribute(loginbutton);
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).click();
		String errormessage1 = driver.findElement(By.xpath("//div[@class='fhs-input-box checked-error']//div[@class='fhs-input-alert']")).getText();
		Assert.assertEquals(errormessage1, "Thông tin này không thể để trống");
		String errormessage2 = driver.findElement(By.xpath("//div[@class='fhs-input-box fhs-input-display checked-error']//div[@class='fhs-input-alert']")).getText();
		Assert.assertEquals(errormessage2, "Thông tin này không thể để trống");
		
		
	}
	
	// chỉ sử dụng default checkbox cho thẻ input, các thẻ khác sử dụng custom	
	@Test
	public void TC_012_Button() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void removeAttribute(WebElement element) {
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
	}
	
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
