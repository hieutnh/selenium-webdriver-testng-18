package webdriver;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert1 {
	WebDriver driver;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		// driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	
	public void TC_01_Alert_Accept() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]")).click();
		sleepInSecond(1);
		//Chuyển qua alert
		alert = driver.switchTo().alert();
		sleepInSecond(1);
		
		//gettex của alert để verify
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		sleepInSecond(1);
		System.out.println("Text của alert là : " + alert.getText());
		
		//accept alert
		alert.accept();
		WebElement veriffyAccept = driver.findElement(By.xpath("//p[@id='result']"));
		veriffyAccept.getText();
		sleepInSecond(1);
		Assert.assertEquals(veriffyAccept.getText(), "You clicked an alert successfully");
		sleepInSecond(1);

	}
	
	
	public void TC_02_Alert_Confrim() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
		
		//Chuyển qua alert
		alert = driver.switchTo().alert();
		sleepInSecond(1);
		
		//gettex của alert để verify
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		sleepInSecond(1);
		System.out.println("Text alert is : " + alert.getText());
		
		//accept alert
		alert.dismiss();
		WebElement veriffyAccept = driver.findElement(By.xpath("//p[@id='result']"));
		veriffyAccept.getText();
		sleepInSecond(1);
		Assert.assertEquals(veriffyAccept.getText(), "You clicked: Cancel");
		sleepInSecond(1);
		
	}
	
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]")).click();
		
		//Chuyển qua alert
		alert = driver.switchTo().alert();
		sleepInSecond(1);
		
		//gettex của alert để verify
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		sleepInSecond(1);
		System.out.println("Text alert is : " + alert.getText());
		sleepInSecond(1);
		//sendkey
		alert.sendKeys("hieutnh");
		sleepInSecond(1);
		
		//accept alert
		alert.accept();
	
		WebElement veriffyAccept = driver.findElement(By.xpath("//p[@id='result']"));
		veriffyAccept.getText();
		sleepInSecond(1);
		Assert.assertEquals(veriffyAccept.getText(), "You entered: hieutnh");
		sleepInSecond(1);

	}
	
	
	public void TC_04_Authentication() {
		String username = "admin";
		String password = "admin";
		
		driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'Basic Auth')]")).isDisplayed());
		
	}
	
	
	public void TC_05_Authentication_Notlink() {
		driver.get("https://the-internet.herokuapp.com/");
			
		String getlinkinvi = driver.findElement(By.xpath("//a[contains(text(),'Basic Auth')]")).getAttribute("href");
		System.out.println(getlinkinvi);
		sleepInSecond(2);
		authenticationNotLink(getlinkinvi);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'Basic Auth')]")).isDisplayed());
		
	}
	
	//biến chung cho TC_05 not link
	public void authenticationNotLink(String linkweb) {
		String splitling[] = linkweb.split("//");
		String username = "admin";
		String password = "admin";
		//Hàm split chia 1 link web thành 2 mảng
		//[0] : Http:
		//[1] : link của web vd: tiki.vn
		linkweb = splitling[0] + "//" + username + ":" + password + "@" + splitling[1];
		driver.get(linkweb);
	}
	
	
	public void TC_06_Authentication_Notlink() {
		driver.get("https://the-internet.herokuapp.com/");
			
	
		
	}
	
	
	//Note : 2 case này sẽ ko hoạt động trên hàm alert.
	//	alert.authenticateUsing(arg0);
	//	alert.setCredentials(arg0);
	


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
