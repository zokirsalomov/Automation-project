package pomDesign;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.WebOrdersLoginPage;

public class WebOrdersLoginTest {
 

	WebDriver driver;
	Faker faker;	
		
		@BeforeClass //runs once for all tests
		public void setUp() {
			System.out.println("Setting up WebDriver in BeforeClass...");
			WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
		    driver.manage().window().maximize();
		    faker = new Faker();
		}
		
		@Ignore
		@Test
		public void positiveLoginTest() {
			driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
			driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
			driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
			driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		}
		
		@Test(priority=2)
		public void positiveLoginUsingPOM() {
			driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
			WebOrdersLoginPage loginPage = new WebOrdersLoginPage(driver);
			
			loginPage.userName.sendKeys("Tester");
			loginPage.password.sendKeys("test");
			loginPage.loginButton.click();
			
			
		}
		
		@Test(priority=1)
		public void invalidUserNametest() {
			driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
			WebOrdersLoginPage loginPage = new WebOrdersLoginPage(driver);
			loginPage.userName.sendKeys("invalid");
			loginPage.password.sendKeys("test");
			loginPage.loginButton.click();
			String errMsg = loginPage.invalidUserNameMsg.getText();
			
			assertEquals(errMsg, "Invalid Login or Password.");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
