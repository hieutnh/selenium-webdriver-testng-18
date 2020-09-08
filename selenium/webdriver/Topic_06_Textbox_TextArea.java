package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String Userid, Passwordlocator, LoginPageUrl, customerName, dateofbirthinput, addressinput, city, passwordnewuser;
	String date, month, year, state, pin, phone, email, dateofbrithoutput, gender, addressoutput, CustomerID;
	
	//String của edit
	String addressinputEdit, cityEdit, stateEdit, pinEdit, phoneEdit, emailEdit, addressoutputEdit;
	
	
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
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\git\\selenium-webdriver-testng-18\\DriverChrome\\chromedriver.exe");
		driver = new ChromeDriver();
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
		pin = "200000";
		phone = "0123456789";
		email = "taotest" + getRanDomNumber() +"@gmail.com";
		passwordnewuser = "123123";
		
		
		
		//Edit customer
		addressinputEdit = "20 ly thai to\nPhuong 10\nBinh Thanh";
		addressoutputEdit = addressinputEdit.replace("\n", " ");
		cityEdit = "Los Angeles";
		stateEdit = "USA";
		pinEdit = "300000";
		phoneEdit = "0902145785";
		emailEdit = "taoedit" + getRanDomNumber() +"@gmail.com";

	}

	@Test
	public void TC_01_Regist_Account() {
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("testne@gmail.com");
		driver.findElement(By.name("btnLogin")).click();
		Userid = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Passwordlocator = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
	}
	@Test
	public void TC_02_Login	() {
		driver.navigate().back();
		driver.navigate().back();
		driver.findElement(By.name("uid")).sendKeys(Userid);
		driver.findElement(By.name("password")).sendKeys(Passwordlocator);
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(), "Welcome To Manager's Page of Guru99 Bank");
	}
	
	@Test
	public void TC_03_NewCustomer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(CustomerName).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		driver.findElement(DateOfBrith).sendKeys(dateofbirthinput);
		driver.findElement(AddressTextArea).sendKeys(addressinput);
		driver.findElement(CityTextBox).sendKeys(city);
		driver.findElement(StateTextBox).sendKeys(state);
		driver.findElement(PinTextBox).sendKeys(pin);
		driver.findElement(TelephoneTextBox).sendKeys(phone);
		driver.findElement(EmailTextBox).sendKeys(email);
		driver.findElement(PasswordTextBox).sendKeys(passwordnewuser);
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");
		
		CustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateofbrithoutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressoutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

	}
	
	@Test
	public void TC_04_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(CustomerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), customerName);	
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(), addressinput);	

		
		driver.findElement(AddressTextArea).clear();
		driver.findElement(AddressTextArea).sendKeys(addressinputEdit);
		
		driver.findElement(CityTextBox).clear();
		driver.findElement(CityTextBox).sendKeys(cityEdit);
				
		driver.findElement(StateTextBox).clear();
		driver.findElement(StateTextBox).sendKeys(stateEdit);
		
		driver.findElement(PinTextBox).clear();
		driver.findElement(PinTextBox).sendKeys(pinEdit);
		
		driver.findElement(TelephoneTextBox).clear();
		driver.findElement(TelephoneTextBox).sendKeys(phoneEdit);
		
		driver.findElement(EmailTextBox).clear();
		driver.findElement(EmailTextBox).sendKeys(emailEdit);		
		
		driver.findElement(By.xpath(" //input[@name='sub']")).click();
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressoutputEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), cityEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), stateEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pinEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phoneEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), emailEdit);
		
		
	}
	public int getRanDomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
	
	@AfterClass
		public void afterClass() {
			driver.quit();
	}		
	
}
