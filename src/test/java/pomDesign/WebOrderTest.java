package pomDesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersListPage;
import pages.ProductsPage;
import pages.WebOrdersLoginPage;

public class WebOrderTest {
  

	WebDriver driver;
	WebOrdersLoginPage loginPage;
	AllOrdersListPage allOrdersPage;
	String userId = "Tester";
	String password = "test";
	ProductsPage productsPage;
	
	@BeforeClass //runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    
	}
	
	@BeforeMethod
	public void setUpLocation() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		loginPage = new WebOrdersLoginPage(driver);
		
	}
	
	@Test(description="verify labels and tab links are displayed")
	public void labelsVerification() {
		assertEquals(driver.getTitle(), "Web Orders Login", "LoginPage is not displayed");
		loginPage.logIn(userId, password);
		
//		loginPage.userName.sendKeys(userId);
//		loginPage.password.sendKeys(password);.
//		loginPage.loginButton.click();
		
		allOrdersPage = new AllOrdersListPage(driver);
		assertTrue(allOrdersPage.webOrders.isDisplayed(), "WebOrders is not displayed");
		assertTrue(allOrdersPage.listOfOrders.isDisplayed(), "List Of Orders is not displayed");
		assertEquals(allOrdersPage.welcomeMsg.getText().replace(" | Logout", ""), "Welcome, " + userId + "!");
		assertTrue(allOrdersPage.orderTab.isDisplayed(), "Orders tab is not displayed");
		assertTrue(allOrdersPage.viewAllOrders.isDisplayed(), "view order is not displayed");
		
		
	}
	
	@Test (description = "Verify deafult Products and prices")
	public void avaliableProductsTest() {
		assertEquals(driver.getTitle(), "Web Orders Login", "LoginPage is not displayed. Application is down.");
		loginPage.logIn(userId, password);
		allOrdersPage = new AllOrdersListPage(driver);
		allOrdersPage.viewAllProductss.click();
		
		productsPage = new ProductsPage(driver);
		List<String> exProducts = Arrays.asList("MyMoney","FamilyAlbum","ScreenSaver");
		List<String> actProducts = new ArrayList<>();

//		productsPage.productNames.forEach(elem -> actProducts.add(elem.getText()));
		for (WebElement prod : productsPage.productNames) {
			actProducts.add(prod.getText());
		}
		assertEquals(actProducts, exProducts);
		
		for (WebElement row  : productsPage.productRows) {
			System.out.println(row.getText());
			String[] prodData = row.getText().split(" ");
			switch(prodData[0]) {
			case "MyMoney":
				assertEquals(prodData[1],"$100");
				assertEquals(prodData[2],"8%");
				break;
			case "FamilyAlbum":
				assertEquals(prodData[1],"$80");
				assertEquals(prodData[2],"15%");
				break;
			case "ScreenSaver":
				assertEquals(prodData[1],"$20");
				assertEquals(prodData[2],"10%");
				break;		
		}
			
		}
	}
	
	
	
	
	
	
	
	
	//logout after each test
	@AfterMethod
	public void logout() {
		allOrdersPage.logout();
		
	}
	
	
	
}
