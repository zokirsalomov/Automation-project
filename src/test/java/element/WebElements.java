package element;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElements {

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
	
//	@Test
//	public void myLinks() {
//		driver.get("https://github.com");
//		List<WebElement> links = driver.findElements(By.tagName("a"));
//		int nmbrOfLinks = links.size();
//		System.out.println("number of links: " + nmbrOfLinks);
//		
//		for (WebElement link : links) {
//			if(!link.getText().isEmpty())
//			System.out.println(link.getText());
//		}
//	
//		
//	// add each link text into a list of String
//	List<String> linkNames = new ArrayList<String>();
//		for (WebElement link : links) {
//			if(!link.getText().isEmpty()){
//				linkNames.add(link.getText());
//			}
//		}
//	
//		System.out.println(linkNames.toString());
//	
//	}
	
	@Test
	public void seleniumWebElementsForm() throws InterruptedException {
		driver.get("https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
		
		List<WebElement> textBoxes = driver.findElements(By.xpath("//input[@type='text']"));
		List<WebElement> dropDowns = driver.findElements(By.tagName("select"));
		List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		List<WebElement> radios = driver.findElements(By.xpath("//input[@type='radio']"));
		List<WebElement> bottons = driver.findElements(By.xpath("//*[@id=\"formAccess\"]/div[1]/div/div/button/em"));
		
		
		assertEquals(textBoxes.size(), 2);
		assertEquals(dropDowns.size(), 3);
		assertEquals(radios.size(), 9);
		assertEquals(bottons.size(), 1);
		

		Thread.sleep(2000);
		// enter random name into input boxes
		for (WebElement inputbox : textBoxes) {
			inputbox.sendKeys(faker.name().firstName()+ ","+ faker.name().lastName());
		}
		Thread.sleep(2000);
		// select dropdpwns randomly
		Select selectDropDn ;
		for(WebElement drop : dropDowns) {
			selectDropDn = new Select(drop);
			selectDropDn.selectByIndex(faker.number().numberBetween(0, 3));
		}
		Thread.sleep(2000);
		// check all checkboxes
		for (WebElement checks : checkBoxes) {
			checks.click();
		}
		Thread.sleep(2000);
		// click radio bottons one by one
		for (WebElement radio : radios) {
			radio.click();
			Thread.sleep(1000);
		}
		Thread.sleep(2000);
		//click  bottons
		for (WebElement botton : bottons) {
			botton.click();
		}
		Thread.sleep(3000);
		
		WebElement thankYoumsg = driver.findElement(By.xpath("//*[@id=\"templateWrapperDiv\"]/div/div[1]/span"));

		assertTrue(thankYoumsg.isDisplayed(), "Home work completed successfully ");
		
		
		
	}
	
	@Test
	public void slideShow() throws InterruptedException {
		driver.get("https://www.hbloom.com/Gifts/birthday-flowers");
		List<WebElement> images = driver.findElements(By.tagName("img"));
		List<String> srcs = new ArrayList<String>();
		
		for(WebElement flower: images) {
			srcs.add(flower.getAttribute("src"));
		}
		
		for (String link : srcs) {
			driver.get(link);
			Thread.sleep(1234);
		}
		
	}
	
	
	
	
	
	
}
