package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_WebElement {
	WebDriver driver;
	By EmailTextBox = By.id("mail");
	By AgeUnderCheckbox = By.id("under_18");
	By EduTextArea = By.id("edu");
	By Jobdropdown1 = By.id("job1");
	By Jobdropdown2 = By.id("job2");
	By InterestsCheckbox = By.id("development");
	By Java = By.id("java");
	
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_Element_Displace() {
		if(driver.findElement(EmailTextBox).isDisplayed()) {
			System.out.println("Element is displayed with = " + EmailTextBox);
			driver.findElement(EmailTextBox).sendKeys("Automation Testing");
		} 
		else {System.out.println("Element is not displayed with = " + EmailTextBox);
		}
		
		if(driver.findElement(EduTextArea).isDisplayed()) {
			System.out.println("Element is displayed with = " + EduTextArea);
			driver.findElement(EduTextArea).sendKeys("Automation Testing");	
		}
		else {System.out.println("Element is not displayed with = " + EduTextArea);
		}
		if(driver.findElement(AgeUnderCheckbox).isDisplayed()) {
			driver.findElement(AgeUnderCheckbox).click();	
		}
	
	}

	@Test
	public void TC_02_Element_Enable() {
		driver.navigate().refresh();
		//enabled
		if(driver.findElement(EmailTextBox).isEnabled()) {
			System.out.println("Element is enabled with = " + EmailTextBox);
		} 
		else {System.out.println("Element is not enabled with = " + EmailTextBox);
		}
		
		if(driver.findElement(EduTextArea).isEnabled()) {
			System.out.println("Element is enabled with = " + EduTextArea);	
		}
		else {System.out.println("Element is not enable  with = " + EduTextArea);
		}
		
		if(driver.findElement(AgeUnderCheckbox).isEnabled()) {
			System.out.println("Element is enabled with = " + AgeUnderCheckbox);	
		}
		else {System.out.println("Element is not enable  with = " + AgeUnderCheckbox);
		}
		
		if(driver.findElement(Jobdropdown1).isEnabled()) {
			System.out.println("Element is enabled with = " + Jobdropdown1);	
		}
		else {System.out.println("Element is not enable  with = " + Jobdropdown1);
		}
		
		if(driver.findElement(InterestsCheckbox).isEnabled()) {
			System.out.println("Element is enabled with = " + InterestsCheckbox);	
		}
		else {System.out.println("Element is not enable  with = " + InterestsCheckbox);
		}
		
		if(driver.findElement(By.id("slider-1")).isEnabled()) {
			System.out.println("Element is enabled with = " + "slider-1");	
		}
		else {System.out.println("Element is not enable  with = " + "slider-1");
		}
		
		
		//disabled
		if(driver.findElement(By.id("password")).isEnabled()) {
			System.out.println("Element is enabled with = " + "password");	
		}
		else {System.out.println("Element is not enable  with = " + "password");
		}	
		if(driver.findElement(By.id("radio-disabled")).isEnabled()) {
			System.out.println("Element is enabled with = " + "radio-disabled");	
		}
		else {System.out.println("Element is not enable  with = " + "radio-disabled");
		}	
		if(driver.findElement(By.id("bio")).isEnabled()) {
			System.out.println("Element is enabled with = " + "bio");	
		}
		else {System.out.println("Element is not enable  with = " + "bio");
		}	
		if(driver.findElement(By.id("job3")).isEnabled()) {
			System.out.println("Element is enabled with = " + "job3");	
		}
		else {System.out.println("Element is not enable  with = " + "job3");
		}
		if(driver.findElement(By.id("check-disbaled")).isEnabled()) {
			System.out.println("Element is enabled with = " + "check-disbaled");	
		}
		else {System.out.println("Element is not enable  with = " + "check-disbaled");
		}
		if(driver.findElement(By.id("slider-2")).isEnabled()) {
			System.out.println("Element is enabled with = " + "slider-2");	
		}
		else {System.out.println("Element is not enable  with = " + "slider-2");
		}
		
	}	

	@Test
	public void TC_03_Selected() {
		driver.navigate().refresh();
		driver.findElement(AgeUnderCheckbox).click();
		driver.findElement(Java).click();
		if(driver.findElement(AgeUnderCheckbox).isSelected()) {
			System.out.println("Element is selected with = " + "AgeUnderCheckbox");	
		}
		else {System.out.println("Element is not selected  with = " + "AgeUnderCheckbox");
		}
		if(driver.findElement(Java).isSelected()) {
			System.out.println("Element is selected with = " + "Java");	
		}
		else {System.out.println("Element is not selected  with = " + "Java");
		}
		
		driver.findElement(Java).click();
		if(driver.findElement(Java).isSelected()) {
			System.out.println("Element is selected with = " + "Java");	
		}
		else {System.out.println("Element is not selected  with = " + "Java");
		}
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
