package com.jobApplication;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static org.testng.Assert.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JobApplicationTest {
	WebDriver driver;
	String firstName;
	String lastName;
	int gender;
	String dateOfBirth;
	String email;
	String phoneNumber;
	String city;
	String state;
	String country;
	int annualSalary;
	List<String> technologies;
	int yearsOfExperience;
	String education;
	String github;
	List<String> certifications;
	String additionalSkills;
	String myGitHubURL = "https://github.com/zokirsalomov/Automation-project/tree/master/src/test/java/com/jobApplication";
	String expectedIP;
    String currentURl;
	
	Faker data = new Faker();
	Random random = new Random();
	
	
	@BeforeClass //runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		WebDriver chrome = new ChromeDriver();
		chrome.get("http://www.ip-adress.eu/");
		expectedIP = "IP address: "+chrome.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/h2/span")).getText();
		chrome.close();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().fullscreen();
	}
	
	@BeforeMethod //runs before each @Test
	public void navigateToHomePage() throws InterruptedException {
		System.out.println("Navigating to homepage in @BeforeMethod....");
		driver.get("https://temp-mail.org/en/");
		email = driver.findElement(By.xpath("//*[@id=\"mail\"]")).getAttribute("value");
		
		
		driver.get("https://forms.zohopublic.com/murodil/form/JobApplicationForm/formperma/kOqgtfkv1dMJ4Df6k4_mekBNfNLIconAHvfdIk3CJSQ");	
		firstName = data.name().firstName();
		lastName = data.name().lastName();
		gender = data.number().numberBetween(1,3);
		dateOfBirth = "05-Jun-1988";
		//email = "favutu@shinnemo.com";
		phoneNumber = data.phoneNumber().cellPhone().replace(".", "");
		city=data.address().cityName();
		state=data.address().stateAbbr();
		country=data.address().country();
		annualSalary=data.number().numberBetween(60000, 150000);
		technologies = new ArrayList<String>();
		technologies.add("Java-" + data.number().numberBetween(1,4));
		technologies.add("HTML-" + data.number().numberBetween(1,4));
		technologies.add("Selenium WebDriver-" + data.number().numberBetween(1,4));
		technologies.add("Maven-"+ data.number().numberBetween(1,4));
		technologies.add("Git-"+ data.number().numberBetween(1,4));
		technologies.add("TestNG-"+ data.number().numberBetween(1,4));
		technologies.add("JUnit-"+ data.number().numberBetween(1,4));
		technologies.add("Cucumber-"+ data.number().numberBetween(1,4));
		technologies.add("API Automation-"+ data.number().numberBetween(1,4));
		technologies.add("JDBC-"+ data.number().numberBetween(1,4));
		technologies.add("SQL-"+ data.number().numberBetween(1,4));
		
		yearsOfExperience = data.number().numberBetween(1, 11);
		education = data.number().numberBetween(1, 4)+"";
		github = "https://github.com/CybertekSchool/selenium-maven-automation.git";
		certifications = new ArrayList<String>();
		certifications.add("Java OCA");
		certifications.add("AWS");
		additionalSkills = data.chuckNorris().fact().toString();
		
		
	}
	
	//=================================================
	String actualName;
	String sumbissionMessage;
	String actualIPAddress;
	String actualLocation; 
	String actualGender;
	String expectedGender; 
	String actualDateOfBirth;
	String actualEmail;
	String actualPhoneNumber;
	String actualAddress;
	String expectedCountry;
	String actualTechnologies;
	String expectedTechnologies;
	String actualAnnualSalary;
	
	String actualYearsOfExperience;
	String expectedYearsOfExperience;
	String actualEducation;
	String expectedEducation;
	String actualGithub;
	String actualCertification; 
	String expectedCertification;
	String actualAdditionalSkills;
	
	//====================================================
	@Test
	public void submitFullApplication() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='Name_First']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='Name_Last']")).sendKeys(lastName);
		setGender(gender);
		driver.findElement(By.xpath("//input[@id='Date-date']")).sendKeys(dateOfBirth);
		driver.findElement(By.xpath("//input[@name='Email']")).clear();
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='countrycode']")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@name='Address_City']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='Address_Region']")).sendKeys(state);
		Select countryElem = new Select(driver.findElement(By.xpath("//select[@id='Address_Country']")));
		countryElem.selectByIndex(data.number().numberBetween(1, countryElem.getOptions().size()));
		expectedCountry = countryElem.getFirstSelectedOption().getText();
		
		driver.findElement(By.xpath("//input[@name='Number']")).sendKeys(String.valueOf(annualSalary)+Keys.TAB);
		verifySalaryCalculations(annualSalary);
		driver.findElement(By.xpath("//em[.=' Next ']")).click();
		

		// SECOND PAGE ACTIONS
		setSkillset(technologies);
		
		driver.findElement(By.xpath("//a[@rating_value='"+ yearsOfExperience +"']")).click();
		expectedYearsOfExperience = "" + driver.findElement(By.xpath("//a[@rating_value='"+ yearsOfExperience +"']")).getAttribute("value");
		
		
		Select educationList = new Select(driver.findElement(By.xpath("//select[@name='Dropdown']")));
		educationList.selectByIndex(data.number().numberBetween(1, educationList.getOptions().size()));
		expectedEducation = educationList.getFirstSelectedOption().getText();
		// sending git hub URL
		driver.findElement(By.xpath("//input[@name='Website']")).sendKeys(myGitHubURL);
		// clicking on certifications
		clickCertifications(data.number().numberBetween(1, 4));
		
		//sending  additional skills
		driver.findElement(By.xpath("//textarea[@name='MultiLine']")).clear();
		driver.findElement(By.xpath("//textarea[@name='MultiLine']")).sendKeys(additionalSkills);
		
		driver.findElement(By.xpath("//em[.='Apply']")).click();
		
		Thread.sleep(1000);
		
		validateEachValue();
		Thread.sleep(3000);
		
	    driver.navigate().back();
	    Thread.sleep(3000);
	    driver.navigate().back();
	   
	    
	    driver.findElement(By.linkText("training@cybertekschool.com")).click();
		
	    WebElement msgTable = driver.findElement(By.xpath("//div[@data-x-div-type='body']/table[1]"));
	    
	    assertTrue(msgTable.isDisplayed());
	    
	    driver.close();
	    System.out.println("Mission Impossible Completed");
		
	}
	
	
	public void validateEachValue() {
		
		actualName = driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[1]")).getText();
		sumbissionMessage= driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[3]")).getText();
		actualIPAddress = driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[6]")).getText();
		actualLocation = driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[7]")).getText();
	
		//Gender:Male
		if(gender == 1) {
			expectedGender = "Gender:Male";
		}else {
			expectedGender = "Gender:Female";
		}
	    actualGender =  driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[9]")).getText();
	    
		actualDateOfBirth = driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[10]")).getText();
		actualEmail = driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[11]")).getText();
		actualPhoneNumber = driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[12]")).getText();
	    actualAddress = driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[13]")).getText();
		
	    actualAnnualSalary = driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[14]")).getText();
	    actualTechnologies = driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[15]")).getText();
		actualYearsOfExperience = driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[16]")).getText();
		actualEducation = driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[17]")).getText();
		actualGithub = driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[18]")).getText();
		actualCertification = driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[19]")).getText();
		actualAdditionalSkills = driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[20]")).getText();
		
		
		
		
		assertEquals(actualName, "Dear "+ firstName+" "+ lastName+",");
		assertTrue(driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[3]")).isDisplayed());
		
		assertEquals(actualIPAddress, expectedIP);
		assertTrue(driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[7]")).isDisplayed());
		
		assertEquals(actualGender, expectedGender);
		assertEquals(actualDateOfBirth, "Date of birth: " + dateOfBirth);
		assertEquals(actualEmail, "Email: " + email);
		assertEquals(actualPhoneNumber, "Phone: " + phoneNumber);
		assertEquals(actualAddress, "Address: "+ city+ ", "+ state +", "+ expectedCountry);
		assertEquals(actualAnnualSalary, "Annual Salary: "+ annualSalary);
		assertEquals(actualTechnologies, expectedTechnologies.trim());
		assertEquals(actualYearsOfExperience, "Years of Experience: "+ expectedYearsOfExperience);
		assertEquals(actualEducation, "Education: " + expectedEducation);
		assertEquals(actualGithub, "GitHub: "+ myGitHubURL);
		assertEquals(actualCertification, expectedCertification);
		assertEquals(actualAdditionalSkills,"Additional Skills: " + additionalSkills);
		
		
	}
	public void setSkillset(List<String> tech) {
	
		expectedTechnologies = "Technologies: ";
		for (String skill : tech) {
			String technology = skill.substring(0, skill.length()-2);
			int rate = Integer.parseInt(skill.substring(skill.length()-1));
			
			String level = "";
			
			switch(rate) {
			case 1:
				level = "Expert";
				break;
			case 2:
				level = "Proficient";
				break;
			case 3:
				level = "Beginner";
				break;
			default:
				fail(rate + " is not a valid level");
			}
			expectedTechnologies += technology+ " : " + level+"  ";
			String xpath = "//input[@rowvalue='"+ technology +"' and @columnvalue='"+ level +"']";
			driver.findElement(By.xpath(xpath)).click();
			
		}
		
		
	}
	public void setGender(int n) {
		if(n==1) {
			driver.findElement(By.xpath("//input[@value='Male']")).click();
		}else {
			driver.findElement(By.xpath("//input[@value='Female']")).click();
		}
	}
	
	public void clickCertifications(int n) {
				
		expectedCertification = "Certifications: ";
		
		String xpathOfOCA = "//input[@value='Java OCA']";
		String xpathOfAWS = "//input[@value='AWS']";
		String xpathOfScrum = "//input[@value='Scrum Master'";
		
		String [] xarr = {xpathOfOCA, xpathOfAWS, xpathOfScrum};
		
		switch(n) {
		case 1:
			driver.findElement(By.xpath(xarr[0])).click();
			expectedCertification += driver.findElement(By.xpath(xarr[0])).getAttribute("value");
			break;
		case 2:
			driver.findElement(By.xpath(xarr[0])).click();
			driver.findElement(By.xpath(xarr[1])).click();
			expectedCertification += driver.findElement(By.xpath(xarr[0])).getAttribute("value")+","+
									 driver.findElement(By.xpath(xarr[1])).getAttribute("value");
			break;
		case 3:
			driver.findElement(By.xpath(xarr[0])).click();
			driver.findElement(By.xpath(xarr[1])).click();
			driver.findElement(By.xpath(xarr[2])).clear();
			
			expectedCertification += driver.findElement(By.xpath(xarr[0])).getAttribute("value")+","+
									 driver.findElement(By.xpath(xarr[1])).getAttribute("value")+","+
									 driver.findElement(By.xpath(xarr[2])).getAttribute("value");
			break;
		
			
		}
		
	
		
	}
	


	public void verifySalaryCalculations(int annual) {
		String monthly = driver.findElement(By.xpath("//input[@name='Formula']")).getAttribute("value");
		String weekly = driver.findElement(By.xpath("//input[@name='Formula1']")).getAttribute("value");
		String hourly =  driver.findElement(By.xpath("//input[@name='Formula2']")).getAttribute("value");
		
		System.out.println(monthly);
		System.out.println(weekly);
		System.out.println(hourly);
		
		DecimalFormat formatter = new DecimalFormat("#.##");
		
		assertEquals(Double.parseDouble(monthly),Double.parseDouble(formatter.format((double)annual /12.0)));
		assertEquals(Double.parseDouble(weekly),Double.parseDouble(formatter.format((double)annual / 52.0)));
		assertEquals(Double.parseDouble(hourly),Double.parseDouble(formatter.format((double)annual / 52.0 / 40.0)));
	}
	
	//Sun Nov 27 04:04:22 EST 1977
//	public void setDateOfBirth(String bday) {
//		String[] pieces = bday.split(" ");
//		String birthDay = pieces[2] + "-" +  pieces[1] + "-" + pieces[5];
//		driver.findElement(By.xpath("//input[@id='Date-date']")).sendKeys(birthDay);
//		
//		
//	}
	
	
	@Test
	public void fullNameEmptyTest() {
		//firstly assert that you are on the correct page
		assertEquals(driver.getTitle(), "SDET Job Application");
		
		driver.findElement(By.xpath("//input[@elname='first']")).clear();	
		driver.findElement(By.xpath("//*[@elname='last']")).clear();

		driver.findElement(By.xpath("//em[.=' Next ']")).click();

		String nameError = driver.findElement(By.xpath("//p[@id='error-Name']")).getText();
		System.out.println("error message : " + nameError);
		//assertEquals(nameError, "Enter a value for this field.");
	}
}