package com.cybertek;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchTests {

	@Test
	public void amazonSearchOne() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://amazon.com");
		String str = "Selenium Testing tools CookBook";
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(str + Keys.ENTER);
		
		String xpath = "//h2[@class='a-size-medium s-inline  s-access-title  a-text-normal'][.='Selenium Testing Tools Cookbook']";
		
		Assert.assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed());
		
		driver.findElement(By.id("twotabsearchtextbox")).clear();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java OCA book"+ Keys.ENTER);
		
		Thread.sleep(2000);
		
		  try {
		      // if the element is still in the html, this line line will handle
		      Assert.assertFalse(driver.findElement(By.xpath(xpath)).isDisplayed());
		    } catch (NoSuchElementException e) {
		      // if the element is not in the html at all, exceptoin will be thrown
		      // since are verify sth does not exist, NoSuchElementException means test should
		      // pass
		      e.printStackTrace();
		    }
		
		
	}
}
