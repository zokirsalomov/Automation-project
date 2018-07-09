package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalInfoPage {

	public PersonalInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="ctl00_MainContent_fmwOrder_ddlProduct")
	public WebElement selectProduct;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_txtQuantity")
	public WebElement quantityOfOrder;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_txtName")
	public WebElement cutomerName;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox2")
	public WebElement street;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox3")
	public WebElement city;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox4")
	public WebElement state;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox5")
	public WebElement zipCode;
	
	@FindBy(xpath="//table[@id='ctl00_MainContent_fmwOrder_cardList']/tbody/tr/td/input")
	public List<WebElement> cardType;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox6")
	public WebElement cardNumber;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox1")
	public WebElement expDate;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_InsertButton")
	public WebElement processButton;
	
	
	
	
	
	
}
