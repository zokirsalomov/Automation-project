package com.jobApplication;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import io.github.bonigarcia.wdm.WebDriverManager;
public class MocharooTesting {

	BufferedReader br;
	FileReader fr;
	BufferedReader br2;
	FileReader fr2;

	List<String> cityList;
	List<String> countryList;
	WebDriver driver;
	String filedName;
	String type;		
	String option;
		
		@BeforeClass
		public void classSetup() throws FileNotFoundException {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			fr = new FileReader("C:\\Users\\Zokir\\Downloads\\MOCK_DATA (4).CSV");
			fr2 = new FileReader("C:\\Users\\Zokir\\Downloads\\MOCK_DATA (4).CSV");
			br = new BufferedReader(fr);
			br2 = new BufferedReader(fr2);
		}
		
		@Test
		public void mainTest() throws InterruptedException, IOException {
			driver.get("https://mockaroo.com/");
			String expected = "Mockaroo - Random Data Generator and API Mocking Tool | JSON / CSV / SQL / Excel";
			String actual = driver.getTitle();
			assertEquals(actual, expected);
			assertTrue(driver.findElement(By.xpath("//div[@class='brand']")).isDisplayed());
			assertTrue(driver.findElement(By.xpath("//div[@class='tagline']")).isDisplayed());
			
			removeXfields();

			assertTrue(driver.findElement(By.xpath("//div[@class='column column-header column-name']")).isDisplayed());
			assertTrue(driver.findElement(By.xpath("//div[@class='column column-header column-type']")).isDisplayed());
			assertTrue(driver.findElement(By.xpath("//div[@class='column column-header column-options']")).isDisplayed());
			assertTrue(driver.findElement(By.xpath("//a[.='Add another field']")).isEnabled());
			
			String numberOfRows = "1000";
			String actualNumberOfRows = driver.findElement(By.xpath("//input[@id='num_rows']")).getAttribute("value");
			assertEquals(actualNumberOfRows, numberOfRows);
			
			String expected2 = "CSV";
			String actual2 = driver.findElement(By.xpath("//select[@id='schema_file_format']/option[1]")).getText();
			assertEquals(actual2, expected2);
			
			String expected3 = "Unix (LF)";
			String actual3 = driver.findElement(By.xpath("//select[@id='schema_line_ending']/option[1]")).getText();
			assertEquals(actual3, expected3);
			assertTrue(driver.findElement(By.xpath("//input[@id='schema_include_header']")).isSelected());
			
			assertFalse(driver.findElement(By.xpath("//input[@id='schema_bom']")).isSelected());
			
			driver.findElement(By.linkText("Add another field")).click();
	        Thread.sleep(1000);
	        driver.findElement(By.xpath("(//input[@placeholder='enter name...'])[7]")).sendKeys("city");
	        
	        driver.findElement(By.xpath("//*[@id=\"fields\"]/div[7]/div[3]/input[3]")).click();
	        
	        assertTrue(driver.findElement(By.xpath("//*[@id=\"type_dialog_wrap\"]")).isDisplayed());
	       

	        driver.findElement(By.xpath("//*[@id=\"type_search_field\"]")).sendKeys("city");
	        Thread.sleep(3000);
	        driver.findElement(By.xpath("//*[@id=\"type_list\"]/div/div[1]")).click();
	       // redoing steps 12-14
	     
	        Thread.sleep(3000);
	        driver.findElement(By.xpath("//*[@id=\"schema_form\"]/div[2]/div[3]/div[2]/a")).click();
	        driver.findElement(By.xpath("(//input[@placeholder='enter name...'])[8]")).sendKeys("country");
            
	        driver.findElement(By.xpath("//*[@id=\"fields\"]/div[8]/div[3]/input[3]")).click();
	        
	        assertTrue(driver.findElement(By.xpath("//*[@id=\"type_dialog_wrap\"]")).isDisplayed());
	        Thread.sleep(3000);
	        driver.findElement(By.xpath("//*[@id=\"type_search_field\"]")).clear();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//*[@id=\"type_search_field\"]")).sendKeys("country");
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//*[@id=\"type_list\"]/div[1]/div[1]")).click();
	        Thread.sleep(2000);
	      //<button class="btn btn-success" id="download" type="button">Download Data</button>
	        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
	        
	  String expectedCity = driver.findElement(By.xpath("(//input[@placeholder='enter name...'])[7]")).getAttribute("value");
	  String expectedCountry = driver.findElement(By.xpath("(//input[@placeholder='enter name...'])[8]")).getAttribute("value");
	  
	  String actualCityCountry = br2.readLine().toString();
	  
	  assertEquals(actualCityCountry, expectedCity+ ","+ expectedCountry);
	   
	  assertEquals(brCount(), 1000);
	 
	  addCities();
	  System.out.println( "cityList's size:  "+ cityList.size());
	  System.out.println( "countryList's size:  "+ countryList.size());     
	    
	  task22and23();
	
	  Set<String> citySet = new HashSet<String>(cityList);
	  assertEquals(countUniqueCities(), citySet.size());

	  Set<String> countrySet = new HashSet<String>(countryList);
	  assertEquals(countUniqueCountries(), countrySet.size());
		
	}
		
		
	public void removeXfields() {
	  for(int i =1; i<=6; i++) {
		  driver.findElement(By.xpath("//*[@id=\"fields\"]/div["+i+"]/div[5]/a")).click();
	  }
			
	}
	
	public int brCount() throws IOException {
		int count = 0;
		while (br2.readLine() != null) {
			count++;
	  	}
       return count;
	}
	
	
	public void addCities() throws IOException {
		cityList = new ArrayList<String>();
		countryList = new ArrayList<String>();
		String temp = "";
		String cities = "";
		int index = 0;
		br.readLine();
		for(int i=1; i<=1000; i++) {
			temp = br.readLine();
			if(temp.contains(",")) {
				index = temp.indexOf(",");
			}else if(temp.contains(" ")) {
				index = temp.indexOf(" ");
			}
			cityList.add(temp.substring(0, index));
			countryList.add(temp.substring(index+1));
			
		}
	
	}
	
	public void task22and23() {
		
		Collections.sort(cityList);
		int max =0;
		int indexOfMax=0;
		int min = cityList.get(0).length();
		int indexOfMin = 0;
		
		for (int i =0; i<cityList.size(); i++) {
			if(cityList.get(i).length()>max) {
				max = cityList.get(i).length();
				indexOfMax = i;
			}
		}
		System.out.println("Longest city name: "+ cityList.get(indexOfMax).toString());
		
		for (int i =0; i<cityList.size(); i++) {
			if(cityList.get(i).length()<min) {
				min = cityList.get(i).length();
				indexOfMin = i; 
			}
		}
		System.out.println("Shortest city name: " + cityList.get(indexOfMin));

		// task 23
		Collection<String > countries  = new ArrayList<String>(countryList);
		Set<String> sortedCountries = new HashSet<String>(countryList);
		for (String eachCountry : sortedCountries) {
			System.out.print(eachCountry + "-");
			System.out.println(Collections.frequency(countries, eachCountry));
		}
		

		}
	
	
	public int countUniqueCities() {
		
		Collection<String> cities = new ArrayList<String>(cityList);
		
		Collection<String> citySet = new ArrayList<String>();

		for (String eachCities : cities) {
			if ( !citySet.contains(eachCities)) {
				citySet.add(eachCities);
			}
		}
			return citySet.size();
	}
	
	

	public int countUniqueCountries() {
		Collection<String> countries = new ArrayList<String>(countryList);
		
		Collection<String> countrySet = new ArrayList<String>();
		
		for (String eachCountries : countries) {
			if (!(countrySet.contains(eachCountries))) {
				countrySet.add(eachCountries);
				
			}
		}
		
		return countrySet.size();
	}
	
	
	
	
	
}
	
	
	
	
	

