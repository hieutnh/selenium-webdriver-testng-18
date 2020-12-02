package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_popup {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\Driver_Browser\\chromedriver.exe");
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

	public void TC_02_Fix_Popup2() {
		driver.get("https://bni.vn/");
		sleepInSecond(2);
		WebElement showregisterForm = driver.findElement(By.xpath("//div[@class='sgpb-popup-builder-content-1236 sgpb-popup-builder-content-html']"));
		Assert.assertTrue(showregisterForm.isDisplayed());
		sleepInSecond(2);
		driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();
		sleepInSecond(2);
		Assert.assertFalse(showregisterForm.isDisplayed());
	}

	@Test
	public void TC_03_Random_Popup() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(20);
		WebElement showregisterForm = driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']"));
		if (showregisterForm.isDisplayed()) {
			Assert.assertTrue(driver.findElement(By.xpath("//img[@class='right-arr lazyloaded']")).isDisplayed());
			sleepInSecond(2);
			driver.findElement(By.xpath("//div[@id='close-mailch']//*[local-name()='svg']")).click();
			sleepInSecond(2);
		}

		driver.findElement(By.xpath("//section[@id='search-2']//input[@placeholder='Search Articles']")).sendKeys("Selenium");
		sleepInSecond(2);
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
		sleepInSecond(2);

		List<WebElement> allselenium = driver.findElements(By.xpath("//h3[@class='post-title']"));

		for (WebElement listselenium : allselenium) {
			// hàm trim xóa khoảng cách và xuống dòng đi
			// gettext thì ra toàn bộ text trong element này
			String listseleniumText = listselenium.getText().trim();
			// contains chỉ lấy ra text "Selenium" để verify
			Assert.assertTrue(listseleniumText.contains("Selenium"));
		}

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
