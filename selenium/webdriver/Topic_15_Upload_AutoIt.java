package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Upload_AutoIt {
	
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		

	}
	
	@Test
	public void TC_01_Upload_File() {
		driver.get("http://192.168.1.80/DaikinSearch/html/top.html");
		driver.findElement(By.xpath("//input[@name='SBU']")).click();
//		List<WebElement> listStartUpload = driver.findElements(By.xpath("//td//button[@class='btn btn-primary start']"));
//		for (WebElement clickListStartUpload : listStartUpload) {
//			System.out.println("Total item =" + listStartUpload.size());
//				if (clickListStartUpload.getText().equals(listStartUpload)) {
//					// Scroll truowcs khi click
//					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
//					sleepInSecond(1);
//					break;
//		}	
		
		
		verifyAllItemVRV("//td[@class='CellBoderBottom  CellBoderLeft']", "VRV");
		verifyAllItemVRV("//td[@class='CellBoderBottom CellColor1 CellBoderLeft']", "VRV");
	}




	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// get page id
	public void switchToWindowByID(String pageID) {
		// Lấy ra tất cả id của window/tab đang có
		// dùng set do chỉ lấy ra giá trị duy nhất, còn List sẽ lấy ra giá trị trùng nhau
		Set<String> allWindows = driver.getWindowHandles();
		// Dùng vòng lặp duyệt qua các ID
		for (String listWindown : allWindows) {
			// Kiểm tra title của page nào bằng với title của page expected thì truyền vào
			if (!listWindown.equals(pageID)) {
				// Swtich vào từng window/tab
				driver.switchTo().window(listWindown);
				// Thoát vòng lặp
				break;
			}
		}
	}
	
	//Verify image bằng JavaScript Executor
	public boolean verifyImage (String imagelocator) {
		boolean isImageLoaded = (Boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				driver.findElement(By.xpath(imagelocator)));
		return isImageLoaded;
	}

	// Close all window/tab
	public boolean closeAllWindow(String parentID) {
		// Lấy ra tất cả ID
		Set<String> idWindow = driver.getWindowHandles();
		// Dùng vòng lặp duyệt qua từng ID
		for (String allidwindow : idWindow) {
			// Nếu như ID khác với ID của parent
			if (!allidwindow.equals(parentID)) {
				// Switch vào ID đó
				driver.switchTo().window(allidwindow);
				driver.close();
			}
		}

		driver.switchTo().window(parentID);
		if(driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}
	
	//Click javascript
	public void clickJvscript(String clicklocator) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath(clicklocator)));
	}

	// Dùng cho custom dropdown
		public void selectItemInDropDown(String ItemLocator, String expectedItem) {
			List<WebElement> allItems = driver.findElements(By.xpath(ItemLocator));
			// Tổng số lượng item trong 1 dropdown
			System.out.println("Item size =" + allItems.size());
			// Duyệt qua từng item
			for (WebElement item : allItems) {
				// Verify với text mình cần click

				if (item.getText().equals(expectedItem)) {
					// Scroll truowcs khi click
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(1);
					break;
				}
			}
		}
		
	public void verifyAllItemVRV(String ItemLocator, String expectedItem) {
		List<WebElement> allItemVRV = driver.findElements(By.xpath(ItemLocator));
		System.out.println("Item size =" + allItemVRV.size());
		for (WebElement listAllItemVRV : allItemVRV) {
				if (listAllItemVRV.getText().equals(expectedItem)) {
					sleepInSecond(1);
					break;
				}
		}
	}
	
	
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
