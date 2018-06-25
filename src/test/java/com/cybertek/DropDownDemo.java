package com.cybertek;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDownDemo {
//// https://tutorialehtml.com/en/html-tutorial-drop-down-lists-menu/
	public static void main(String[] args) {
		
		 WebDriverManager.chromedriver().setup();
		  WebDriver driver = new ChromeDriver();
		  driver.get("https://tutorialehtml.com/en/html-tutorial-drop-down-lists-menu/");
		  
		  // 1.find select ag
		  
		  WebElement selectTag = driver.findElement(By.name("my_html_select_box"));
		  // 2. create select object from the select tag
		  
		  Select list = new Select(selectTag);
		  
		  //list.getFirstSelectedOption();
		
		  WebElement selected = list.getFirstSelectedOption();
		  System.out.println(selected.getText());
		  System.out.println(list.getFirstSelectedOption().getText());
		  
	}
	
}
