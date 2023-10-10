package kang.practice.selenium_hybrid.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import kang.practice.selenium_hybrid.base.Base;
import kang.practice.selenium_hybrid.pages.AccountPage;
import kang.practice.selenium_hybrid.pages.HomePage;
import kang.practice.selenium_hybrid.pages.RegisterPage;
import kang.practice.selenium_hybrid.utils.Utilities;

public class RegisterTest extends Base{

	public WebDriver driver;
	RegisterPage registerPage;
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@BeforeMethod
	public void setup() {
		
		loadPropertiesFile();
		driver = intitializeUrlinBrowser(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.clickRegisterButton();

	}
	
	@Test(priority=1)
	public void verifyRegisterWithMandatoryFields() {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterTelephone(dataProp.getProperty("telNo"));
		registerPage.enterEmail(Utilities.generateUniqueEmail());
		registerPage.enterPwd(dataProp.getProperty("newPwd"));
		registerPage.enterConfirmPwd(dataProp.getProperty("newPwd"));
		registerPage.clickAgreeCheckbox();
		registerPage.clickContinue();
		
		AccountPage myAccountPage = new AccountPage(driver);
		AssertJUnit.assertEquals(myAccountPage.successMssgText(), "Your Account Has Been Created!");
	}
	
	@Test(priority=2)
	public void verifyRegisterWithAllFields() {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterTelephone(dataProp.getProperty("telNo"));
		registerPage.enterEmail(Utilities.generateUniqueEmail());
		registerPage.enterPwd(dataProp.getProperty("newPwd"));
		registerPage.enterConfirmPwd(dataProp.getProperty("newPwd"));
		registerPage.clickAgreeCheckbox();
		registerPage.clickToSubscribeNewsletter();
		registerPage.clickContinue();
		AccountPage myAccountPage = new AccountPage(driver);
		AssertJUnit.assertEquals(myAccountPage.successMssgText(), "Your Account Has Been Created!");
	}
	
	@Test(priority=3)
	public void verifyRegisterWithExistingEmail() {

		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterTelephone(dataProp.getProperty("telNo"));
		registerPage.enterEmail("samzell1@yahoo.com");
		registerPage.enterPwd(dataProp.getProperty("newPwd"));
		registerPage.enterConfirmPwd(dataProp.getProperty("newPwd"));
		registerPage.clickAgreeCheckbox();
		registerPage.clickContinue();
		
		AssertJUnit.assertEquals(registerPage.getErrorText(), "Warning: E-Mail Address is already registered!");
		
		
	}
	@Test(priority=4)
	public void verifyRegisterWithEmptyFields() {
		
		registerPage.clickContinue();
		AssertJUnit.assertEquals(registerPage.getPrivacyPolicyErrorMssg(), dataProp.getProperty("privacyPolicyWarning"));
		AssertJUnit.assertEquals(registerPage.getFirstNameErrorMssg(), dataProp.getProperty("firstNameWarning"));
		AssertJUnit.assertEquals(registerPage.getLastNameErrorMssg(), dataProp.getProperty("lastNameWarning"));
		AssertJUnit.assertEquals(registerPage.getEmailErrorMssg(), dataProp.getProperty("emailWarning"));
		AssertJUnit.assertEquals(registerPage.getTelephoneErrorMssg(), dataProp.getProperty("telephoneWarning"));
		AssertJUnit.assertEquals(registerPage.getPasswordErrorMssg(), dataProp.getProperty("pwdWarning"));
	}
	

}
