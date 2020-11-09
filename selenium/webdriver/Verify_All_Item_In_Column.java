package webdriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Verify_All_Item_In_Column {

	WebDriver driver;
	WebDriverWait explicitWait;
	String ROOT_FOLDER = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\Driver_Browser\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 60);

		driver.manage().window().maximize();
	}

	public void TC_01_Alert_Accept() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php/backendlogin");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("user01");
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys("guru99com");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'close')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='customerGrid_filter_billing_postcode']")).sendKeys("70000");
		driver.findElement(By.xpath("//span[contains(text(),'Search')]")).click();
		Thread.sleep(3000);
		List<WebElement> numerRows = driver.findElements(By.xpath("//table[@id='customerGrid_table']//tbody//tr"));
		int rowSize = numerRows.size();
		System.out.println("row is:	" + rowSize);
		List<WebElement> numberColumn = driver.findElements(By.xpath("//table[@id='customerGrid_table']//tbody//tr//td[7]"));
		int columnSize = numberColumn.size();
		System.out.println("column is: " + columnSize);
		for (WebElement item : numberColumn) {
			if (item.getText().equals("70000")) {
				Assert.assertEquals(item.getText(), "70000");
			} else {
				item.getText();
				System.out.println("item not equal :" + item.getText());

			}

		}
	}

	@Test
	public void TC_02_Alert_Accept() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php/backendlogin");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("user01");
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys("guru99com");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'close')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='customerGrid_filter_billing_postcode']")).sendKeys("70000");
		driver.findElement(By.xpath("//span[contains(text(),'Search')]")).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'Name')]//ancestor::thead//following-sibling::tbody//td[contains(text(),'Long Pham')]//preceding-sibling::td//input[@type='checkbox']")));
		List<WebElement> johnCena = driver.findElements(By.xpath("//span[contains(text(),'Name')]//ancestor::thead//following-sibling::tbody//td[contains(text(),'Long Pham')]//preceding-sibling::td//input[@type='checkbox']"));
		for (WebElement item : johnCena) {
			if (item.isDisplayed()) {
				item.click();
			} else {
				throw new RuntimeException("Please wait to element visible");
			}
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Export')]")).click();
		Thread.sleep(10000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
