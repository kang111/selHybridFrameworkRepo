package kang.practice.selenium_hybrid.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	//Elements
	
	@FindBy(id="input-email")
	private WebElement inputEmail;
	
	@FindBy(id="input-password")
	private WebElement inputPassword;
	
	@FindBy(xpath="//input[@value = 'Login']")
	private WebElement loginButton;
	
	@FindBy(css="#account-login > div.alert.alert-danger.alert-dismissible")
	private WebElement warningText;
	
	
	//Actions
	public void enterEmail(String email) {
		inputEmail.sendKeys(email);
	}
	public void enterPassword(String pwd) {
		inputPassword.sendKeys(pwd);
	}
	public boolean warningMssgDisplayed() {
		return warningText.isDisplayed();
	}
	public void clickOnLoginButton() {
		loginButton.click();
	}
	public void login(String email, String pwd){
		inputEmail.sendKeys(email);
		inputPassword.sendKeys(pwd);
		loginButton.click();
	}
}
