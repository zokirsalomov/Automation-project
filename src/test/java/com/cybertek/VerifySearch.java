package com.cybertek;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifySearch {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://etsy.com");
		
		driver.findElement(By.id("search-query"));
		String searchTeam = "fathers day gifts";
		
		WebElement input = driver.findElement(By.id("search-query"));
		input.sendKeys(searchTeam+ Keys.ENTER); // page refreshed || directed to results page
		
		input = driver.findElement(By.id("search-query"));
		String actual = input.getAttribute("value");
		
		System.out.println(actual);
		
		if(actual.endsWith(searchTeam)) {
			System.out.println("Pass");
		}else {
			System.out.println("Fail");
			System.out.println("expected: "+ searchTeam);
			System.out.println("found: "+ actual);
		}
		
		
		
		
		
		
	}
}
