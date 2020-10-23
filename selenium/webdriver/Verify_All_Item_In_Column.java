package webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Verify_All_Item_In_Column {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\Driver_Browser\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
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
		List<WebElement> numberColumn = driver.findElements(By.xpath("//table[@id='customerGrid_table']//tbody//tr[1]//td"));
		int columnSize = numberColumn.size();
		System.out.println("column is: " + columnSize);
		for (int i = 1; i <= rowSize; i++) {
//			for (int j = 1; j <= columnSize; j++) {
			for (WebElement item : numberColumn) {
				item.getText().equals("70000");
				Assert.assertEquals(item.getText(), "70000");
			}

			WebElement allitem = driver.findElement(By.xpath("//table[@id='customerGrid_table']//tbody//tr[" + i + "]//td[7]"));
			Assert.assertEquals(allitem.getText(), "70000");
			System.out.println("All column have text expected:  " + allitem.getSize());
//			}
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
