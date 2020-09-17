package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic_12_Frame_Iframe {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	public void TC_01_Iframe_Frame() {
		driver.get("https://kyna.vn/");
		
		WebElement showregisterForm = driver.findElement(By.xpath("//img[@class='fancybox-image']"));
		if (showregisterForm.isDisplayed()) {
		Assert.assertTrue(driver.findElement(By.xpath("//img[@class='right-arr lazyloaded']")).isDisplayed());
		sleepInSecond(2);	
		driver.findElement(By.xpath("//a[@class='fancybox-item fancybox-close']")).click();
		sleepInSecond(2);
		}
		
		String test1 = driver.switchTo().frame(driver.findElement(By.id("u_0_1'")));
		Assert.assertTrue(test1.isDisplayed());
	}
	
	
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
