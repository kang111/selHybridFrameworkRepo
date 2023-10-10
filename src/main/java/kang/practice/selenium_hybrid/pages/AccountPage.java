package kang.practice.selenium_hybrid.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	
	WebDriver driver;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	
	//Elements
	
	@FindBy(linkText="Edit your account information")
	private WebElement editAccountLink;
	@FindBy(css="#content > h1")
	private WebElement successfulRegisterMssg;
	
	
	//Actions
	
	public boolean VerifyLinkIsDisplayed() {
		return editAccountLink.isDisplayed();
	}
	public String successMssgText() {
		return successfulRegisterMssg.getText();
	}
	
}
