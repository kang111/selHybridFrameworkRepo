package kang.practice.selenium_hybrid.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Elements
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	@FindBy(id="input-confirm")
	private WebElement confirmPwdField;
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	@FindBy(id="input-email")
	private WebElement emailField;
	@FindBy(id="input-password")
	private WebElement passwordField;
	@FindBy(xpath="//input[@name='agree']")
	private WebElement agreeCheckBox;
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	@FindBy(css="#content > form > fieldset:nth-child(3) > div > div > label:nth-child(1) > input[type=radio]")
	private WebElement SubscribeNewsLetterCheckbox;
	@FindBy(css="#account-register > div.alert.alert-danger.alert-dismissible")
	private WebElement errorMssg;
	
	@FindBy(css="#account-register > div.alert.alert-danger.alert-dismissible")
	private WebElement privacyPolicyErrorMessage;
	@FindBy(css="#account > div:nth-child(3) > div > div")
	private WebElement firstNameErrorMessage;
	@FindBy(css="#account > div:nth-child(4) > div > div")
	private WebElement lastNameErrorMessage;
	@FindBy(css="#account > div:nth-child(5) > div > div")
	private WebElement emailErrorMessage;
	@FindBy(css="#account > div:nth-child(6) > div > div")
	private WebElement telephoneErrorMessage;
	@FindBy(css="#content > form > fieldset:nth-child(2) > div.form-group.required.has-error > div > div")
	private WebElement passwordErrorMessage;
	
	//Actions
	
	public void enterFirstName(String firstName) {
		// TODO Auto-generated method stub
		firstNameField.sendKeys(firstName);
	}
	public void enterLastName(String lastName) {
		// TODO Auto-generated method stub
		lastNameField.sendKeys(lastName);
	}
	public void enterEmail(String email) {
		// TODO Auto-generated method stub
		emailField.sendKeys(email);
	}
	public void enterTelephone(String phoneNo) {
		// TODO Auto-generated method stub
		telephoneField.sendKeys(phoneNo);
	}
	public void enterPwd(String pwd) {
		// TODO Auto-generated method stub
		passwordField.sendKeys(pwd);
	}
	public void enterConfirmPwd(String confirmPwd) {
		// TODO Auto-generated method stub
		confirmPwdField.sendKeys(confirmPwd);
	}
	public void clickToSubscribeNewsletter() {
		// TODO Auto-generated method stub
		SubscribeNewsLetterCheckbox.click();
	}
	public void clickAgreeCheckbox() {
		// TODO Auto-generated method stub
		agreeCheckBox.click();
	}
	public void clickContinue() {
		// TODO Auto-generated method stub
		continueButton.click();
	}
	public String getErrorText() {
		// TODO Auto-generated method stub
		return errorMssg.getText();
	}
	public String getPrivacyPolicyErrorMssg() {
		// TODO Auto-generated method stub
		return privacyPolicyErrorMessage.getText();
	}
	public String getFirstNameErrorMssg() {
		// TODO Auto-generated method stub
		return firstNameErrorMessage.getText();
	}
	public String getLastNameErrorMssg() {
		// TODO Auto-generated method stub
		return lastNameErrorMessage.getText();
	}
	public String getEmailErrorMssg() {
		// TODO Auto-generated method stub
		return emailErrorMessage.getText();
	}
	public String getTelephoneErrorMssg() {
		// TODO Auto-generated method stub
		return telephoneErrorMessage.getText();
	}
	public String getPasswordErrorMssg() {
		// TODO Auto-generated method stub
		return passwordErrorMessage.getText();
	}
}
