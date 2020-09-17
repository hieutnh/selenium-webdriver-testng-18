package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.sun.jna.platform.win32.OaIdl.ElemDescArg;

public class Topic_12_Frame_Iframe {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	public void TC_01_Fix_Popup() {
		driver.get("https://www.zingpoll.com/");
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[@id='Loginform']")).click();
		sleepInSecond(2);
		WebElement showdialog = driver.findElement(By.xpath("//div[@class='modal-dialog modal_dialog_custom']//div[@class='row']"));
		Assert.assertTrue(showdialog.isDisplayed());
		sleepInSecond(2);
		driver.findElement(By.xpath("//div[@class='modal-dialog modal_dialog_custom']//span[contains(text(),'×')]")).click();
		sleepInSecond(2);
		Assert.assertFalse(showdialog.isDisplayed());
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
