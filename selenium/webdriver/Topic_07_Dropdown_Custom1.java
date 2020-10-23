package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dropdown_Custom1 {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String rootFolder = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

	}

	public void TC_01_JQUERY() {

		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "5");
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'5')]")).isDisplayed());
		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "10");
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'10')]")).isDisplayed());
		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "15");
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'15')]")).isDisplayed());
		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'19')]")).isDisplayed());

	}

	public void TC_02_Angular() {
		driver.get("https://bom.to/GjUg3AR");
		selectItemInDropDown("//ejs-dropdownlist[@id='games']//span[contains(@class, 'e-input-group')]", "//ul[@id='games_options']/li", "American Football");
		Assert.assertEquals(getHidenText("select[id='games_hidden'] option"), "American Football");
		selectItemInDropDown("//ejs-dropdownlist[@id='games']//span[contains(@class, 'e-input-group')]", "//ul[@id='games_options']/li", "Golf");
		Assert.assertEquals(getHidenText("select[id='games_hidden'] option"), "Golf");
	}

	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInDropDown("//div[@role='listbox']", "//div[@role='option']", "Jenny Hess");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Jenny Hess']")).isDisplayed());
		selectItemInDropDown("//div[@role='listbox']", "//div[@role='option']", "Christian");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Christian']")).isDisplayed());

	}

	public void TC_04_vueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInDropDown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li", "First Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
		selectItemInDropDown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li", "Second Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
	}

	public void TC_05_editAble() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		sendkeydropdown("//div[@id='default-place']//input", "Audi");
		Assert.assertEquals(getHidenText("#default-place li.es-visible"), "Audi");
		sendkeydropdown("//div[@id='default-place']//input", "BMW");
		Assert.assertEquals(getHidenText("#default-place li.es-visible"), "BMW");
	}

	@Test
	public void TC_06_Advance() {
		driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		String[] months = { "January", "August", "September" };
		selectMultiInDropDown("//option/parent::select/following-sibling::div", "//option/parent::select/following-sibling::div//label/span", months);
		Assert.assertTrue(checkMultiItemSelected(months));

		driver.navigate().refresh();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		String[] months2 = { "March", "April", "September", "December" };
		selectMultiInDropDown("//option/parent::select/following-sibling::div", "//option/parent::select/following-sibling::div//label/span", months2);
		Assert.assertTrue(checkMultiItemSelected(months2));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Hàm cho sendkey
	public void sendkeydropdown(String locator, String value) {
		driver.findElement(By.xpath(locator)).clear();
		sleepInSecond(1);
		driver.findElement(By.xpath(locator)).sendKeys(value);
		sleepInSecond(1);
		driver.findElement(By.xpath(locator)).sendKeys(Keys.TAB);
		sleepInSecond(1);

	}

	// Dùng cho custom dropdown
	public void selectItemInDropDown(String parentLocator, String ItemLocator, String expectedItem) {
		// Click vào 1 thẻ bất kì
		driver.findElement(By.xpath(parentLocator)).click();
		// Chờ cho tất cả item được xuất hiện
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ItemLocator)));
		// Lấy hết tất cả các item đưa vào 1 list
		List<WebElement> allItems = driver.findElements(By.xpath(ItemLocator));
		// Tổng số lượng item trong 1 dropdown
		System.out.println("Item size =" + allItems.size());
		// Duyệt qua từng item
		for (WebElement item : allItems) {
			// Verify với text mình cần click

			if (item.getText().equals(expectedItem)) {
				// Scroll trước khi click
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	// Dùng có advance Multi Dropdown (TC_06)
	public void selectMultiInDropDown(String locatorMulti, String itemLocatorMulti, String[] ExpectedMulti) {
		// Click vào 1 thẻ bất kì
		driver.findElement(By.xpath(locatorMulti)).click();
		// Chờ cho tất cả item được xuất hiện
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocatorMulti)));
		// Lấy hết tất cả các item đưa vào 1 list
		List<WebElement> allItems = driver.findElements(By.xpath(itemLocatorMulti));
		// Tổng số lượng item trong 1 dropdown
		System.out.println("Item size =" + allItems.size());
		// Duyệt qua từng item
		for (WebElement itemMulti : allItems) {
			// Duyệt qua từng item trong mảng String[]
			for (String itemExpectedString : ExpectedMulti) {
				// Verify với text mình cần click
				if (itemMulti.getText().equals(itemExpectedString)) {
					// Scroll trước khi click
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", itemMulti);
					sleepInSecond(2);
					// Click vào item cần chọn
					jsExecutor.executeScript("arguments[0].click();", itemMulti);
					sleepInSecond(2);
					List<WebElement> ItemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item Selected = " + ItemSelected.size());
					if (ExpectedMulti.length == ItemSelected.size()) {
						break;
					}
				}
			}
		}
	}

	// Verify Multi Dropdown (TC_06)
	public boolean checkMultiItemSelected(String[] ItemMultiSelected) {
		List<WebElement> ItemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		// Số item được chọn = với số item
		int numberItemSelected = ItemSelected.size();

		// gettext trong dropdown
		String allItemSelectedText = driver.findElement(By.xpath("//button[@class='ms-choice']/span")).getText();
		System.out.println("Text is selected =" + allItemSelectedText);

		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			for (String item : ItemMultiSelected) {
				if (allItemSelectedText.contains(item)) {
					break;
				}
			}
			// đúng và true khi nó chứa hết
			return true;
		}
		// Sai nếu >3 text trong dropdown là số
		else {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();

		}
	}

	// get hiden text
	public String getHidenText(String csslocator) {
		return (String) jsExecutor.executeScript("return document.querySelector(\"" + csslocator + "\").textContent");
	}

	// ***chỉ sử dụng default dropdown cho thẻ option, các thẻ khác sử dụng custom

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}