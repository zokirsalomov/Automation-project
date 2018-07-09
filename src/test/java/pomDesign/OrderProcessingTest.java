package pomDesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersListPage;
import pages.PersonalInfoPage;
import pages.ProductsPage;
import pages.WebOrdersLoginPage;

public class OrderProcessingTest {

	WebDriver driver;
	WebOrdersLoginPage loginPage;
	AllOrdersListPage allOrdersPage;
	ProductsPage productsPage;
	PersonalInfoPage personalInfoPage;
	
	Faker faker;
	Random random;
	
	String userId = "Tester";
	String password = "test";
	
	String custName, streetName, cityName, stateName, zipcode,  cardExpDate;
	String visaCard, masterCar, amexCard;
	
	
	@BeforeClass //runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    
	}
	
	@BeforeMethod
	public void setUpLocation() {
		random = new Random();
		faker  = new Faker();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		loginPage = new WebOrdersLoginPage(driver);
		loginPage.logIn(userId, password);
		
		custName = faker.name().firstName() + " " +faker.name().lastName();
		streetName = faker.address().buildingNumber() + " " + faker.address().streetAddress();
		cityName = faker.address().cityName();
		stateName = faker.address().state();
		zipcode = faker.address().zipCode().substring(0, 5);
		
		visaCard = "4";
		masterCar = "5";
		amexCard = "3";
		for(int i=0; i<15; i++) {
			visaCard +=  random.nextInt(9);
			masterCar +=  random.nextInt(9);
		}
		for(int i=0; i<14; i++) {
			amexCard += random.nextInt(9);
		}
		
		cardExpDate = ""+faker.number().numberBetween(10, 12)+ "/" + faker.number().numberBetween(18, 22);
		
	}
	
	
	@Test
	public void orderProcessingTest() {
		allOrdersPage = new AllOrdersListPage(driver);
		allOrdersPage.orderTab.click();
		
		personalInfoPage = new PersonalInfoPage(driver);
		
		Select select = new Select(personalInfoPage.selectProduct);
		select.selectByIndex(random.nextInt(2)+1);
		personalInfoPage.quantityOfOrder.clear();
		personalInfoPage.quantityOfOrder.sendKeys("1");
		personalInfoPage.cutomerName.sendKeys(custName);
		personalInfoPage.street.sendKeys(streetName);
		personalInfoPage.city.sendKeys(cityName);
		personalInfoPage.state.sendKeys(stateName);
		personalInfoPage.zipCode.sendKeys(zipcode);
		
//		List< String > listOfCardTypes = new ArrayList<>();
//		personalInfoPage.cardType.forEach(elem -> listOfCardTypes.add(elem.getText()));
//		Collections.sort(listOfCardTypes);
		
		int option = random.nextInt(2);
		switch(option) {
		case 0:
			personalInfoPage.cardType.get(0).click();
			personalInfoPage.cardNumber.sendKeys(visaCard);
			break;
		case 1:
			personalInfoPage.cardType.get(1).click();
			personalInfoPage.cardNumber.sendKeys(masterCar);
			break;
		case 2:
			personalInfoPage.cardType.get(2).click();
			personalInfoPage.cardNumber.sendKeys(amexCard);
		}
		
		personalInfoPage.expDate.sendKeys(cardExpDate);
		personalInfoPage.processButton.click();
		
		// order submission message displayed
		assertTrue(driver.findElement(By.xpath("//div[@class='buttons_process']/strong")).isDisplayed());
		
		allOrdersPage.viewAllOrders.click();
		
		String actName = driver.findElement(By.xpath("//table[@class='SampleTable']/tbody/tr[2]/td[2]")).getText();
		assertEquals(actName, custName);
		
		System.out.println("Home work completed");
	}
	
	@AfterMethod
	public void logout() {
		allOrdersPage.logout();
		
	}
	
	
	
	
}
