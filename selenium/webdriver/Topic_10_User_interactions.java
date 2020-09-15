package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JViewport;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_interactions {
	WebDriver driver;
	WebElement element;
	Actions action;
	JavascriptExecutor jsExecutor;
	String rootFolder = System.getProperty("user.dir");
	String javascriptPath = rootFolder + "\\Drag and Drop\\drag_and_drop_helper.js";
	String jQueryPath = rootFolder + "\\Drag and Drop\\jquery_load_helper.js";
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Hover_Mouse() {
		driver.get("http://www.myntra.com/");
		element = driver.findElement(By.xpath("//a[@class='desktop-main'][contains(text(),'Kids')]"));
		// di chuyển chuột tới 1 vị trí
		action.moveToElement(element).perform();
		driver.findElement(By.xpath("//a[contains(text(),'Home & Bath')]")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(),
				"Kids Home Bath");

	}

	public void TC_02_Hover_Mouse() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> allItem = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		//click chuột vào giữ
		action.clickAndHold(allItem.get(0)).moveToElement(allItem.get(3)).release().perform();
		List<WebElement> allItemSelected = driver
				.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(allItemSelected.size(), 4);

		for (WebElement displayitemSelected : allItemSelected) {
			System.out.println(displayitemSelected.getText());
		}

	}


	public void TC_03_Ctrl_And_Click() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> allItem = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		//Nhấn button Ctrl
		action.keyDown(Keys.CONTROL).perform();
		//Click chuột trái
		action.click(allItem.get(0)).click(allItem.get(2)).click(allItem.get(5)).click(allItem.get(10)).perform();
		//Thả button Ctrl
		action.keyUp(Keys.CONTROL).perform();
		List <WebElement> allItemSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(allItemSelected.size(), 4);
		
		for (WebElement displayitemSelected : allItemSelected) {
			System.out.println(displayitemSelected.getText());
		}
	}


	public void TC_04_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		element = driver.findElement(By.xpath("//button[contains(text(),'Double click me')]"));
		//double click chuột
		action.doubleClick(element).perform();
		sleepInSecond(1);
		WebElement verifytext = driver.findElement(By.xpath("//p[@id='demo']"));
		Assert.assertEquals(verifytext.getText(), "Hello Automation Guys!");
		sleepInSecond(1);
		System.out.println("Verify text double text is : " + verifytext.getText());
	}



	public void TC_05_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightclick = driver.findElement(By.xpath("//span[text()='right click me']"));
		//click chuột phải
		action.contextClick(rightclick).perform();
		sleepInSecond(2);
		
		WebElement elementQuit = driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']"));
		action.moveToElement(elementQuit).perform();
		sleepInSecond(2);
		
		//lấy giá trị trong thẻ class
		String verifyQuit = elementQuit.getAttribute("class");
		System.out.println(verifyQuit);
		sleepInSecond(2);
		//verify bằng hàm contains
		Assert.assertTrue(verifyQuit.contains("context-menu-visible"));
		Assert.assertTrue(verifyQuit.contains("context-menu-hover"));
		
		//verify bằng hàm display
		Assert.assertTrue(elementQuit.isDisplayed());
		
		
	}
	

	public void TC_06_Drag_And_Drop_HTML_Nho_Hon_5() {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement sourceCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		Assert.assertEquals(targetCircle.getText(), "You did great!");
	}
	
	@Test
	public void TC_07_Drag_And_Drop_HTML5() throws InterruptedException, IOException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");

		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		String java_script = readFile(javascriptPath);

		// Inject Jquery lib to site
		// String jqueryscript = readFile(jqueryPath);
		// javascriptExecutor.executeScript(jqueryscript);

		// chuyển từ A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(java_script);
		Thread.sleep(2000);
		Assert.assertTrue(checkisdisplay("//div[@id='column-a']/header[text()='B']"));

		// chuyển từ B to A
		java_script = "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(checkisdisplay("//div[@id='column-a']/header[text()='A']"));
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean checkisdisplay(String locator) {
		WebElement displaylocator = driver.findElement(By.xpath(locator));
		if (displaylocator.isDisplayed()){
			return true;
		}
		return false;
	}
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
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
