package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class sfa {
	WebDriver driver;
	String Userid, Password, LoginPageUrl, customerName, dateofbirthinput, addressinput, city;
	String date, month, year, state, pin, phone, email, dateofbrithoutput, gender, addressoutput;
	
	By CustomerName = By.name("name");
	By DateOfBrith = By.name("dob");
	By AddressTextArea = By.name("addr");
	By CityTextBox = By.name("city");
	By StateTextBox = By.name("state");
	By PinTextBox = By.name("pinno");
	By TelephoneTextBox = By.name("telephoneno");
	By EmailTextBox = By.name("emailid");
	By PasswordTextBox = By.name("password");
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		customerName = "Tao Test";
		date = "20";
		month = "09";
		year = "1992";
		gender = "male";
		dateofbirthinput = month + "/" + date + "/" + year;
		dateofbrithoutput = year + "-" + month + "-" + date;
		addressinput = "15 Dien Bien Phu\nPhuong 25\nBinh Thanh";
		addressoutput = addressinput.replace("\n", " ");
		city = "Ho Chi Minh";
		state = "Viet Nam";
		pin = "20000";
		phone = "0123456789";
		email = "taotest" + getRandomNumber() +"@gmail.com";
	}

	@Test
	public void TC_01_Regist_Account() {
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("testne@gmail.com");
		driver.findElement(By.name("btnLogin")).click();
		Userid = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
	}
	
	@Test
	public void TC_02_Verify_Title() {
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Userid);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String HomePage = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();
		Assert.assertEquals(HomePage, "Welcome To Manager's Page of Guru99 Bank");
	}
	
	@Test
	public void TC_03_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(CustomerName).sendKeys("customerName");
		driver.findElement(By.xpath("//input[@value='m']")).click();
		driver.findElement(DateOfBrith).sendKeys("dateofbirthinput");
		driver.findElement(AddressTextArea).sendKeys("addressinput");
		driver.findElement(CityTextBox).sendKeys("city");
		driver.findElement(StateTextBox).sendKeys("state");
		driver.findElement(PinTextBox).sendKeys("pin");
		driver.findElement(TelephoneTextBox).sendKeys("phone");
		driver.findElement(EmailTextBox).sendKeys("email");
		driver.findElement(PasswordTextBox).sendKeys("Password");
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		String VerifyRegist = driver.findElement(By.xpath("//p[@class='heading3']")).getText();
		Assert.assertEquals(VerifyRegist, "Customer Registered Successfully!!!");
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")), dateofbrithoutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")), addressoutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")), email);
		
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	
	
	@AfterClass
		public void afterClass() {
			driver.quit();
	}		
	
}
