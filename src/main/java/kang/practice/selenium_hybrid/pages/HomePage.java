package kang.practice.selenium_hybrid.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	//Constructor
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Elements
	
	private WebDriver driver;
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropdown;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(css="#search > input")
	private WebElement searchField;
	
	@FindBy(css="#search > span > button")
	private WebElement searchButton;
	
	@FindBy(linkText="Register")
	private WebElement registerDropdown;
	
	//Actions
	
	public void clickOnMyAccount() {
		myAccountDropdown.click();
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	public void enterValidProductName(String name) {
		searchField.sendKeys(name);
	}
	
	public SearchResultsPage clickSearchButton() {
		searchButton.click();
		return new SearchResultsPage(driver);
	}
	public RegisterPage clickRegisterButton() {
		registerDropdown.click();
		return new RegisterPage(driver);	}
}
