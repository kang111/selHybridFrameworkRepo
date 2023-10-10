package kang.practice.selenium_hybrid.testcases;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import kang.practice.selenium_hybrid.base.Base;
import kang.practice.selenium_hybrid.pages.AccountPage;
import kang.practice.selenium_hybrid.pages.HomePage;
import kang.practice.selenium_hybrid.pages.LoginPage;
import kang.practice.selenium_hybrid.utils.Utilities;

public class LoginTest extends Base{
	
	public WebDriver driver;
	public LoginPage loginPage;
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@BeforeMethod
	public void sessionSetup() {
		
		loadPropertiesFile();
		driver = intitializeUrlinBrowser(prop.getProperty("browser"));
		HomePage appHomePage = new HomePage(driver);
		appHomePage.clickOnMyAccount();
		loginPage = appHomePage.selectLoginOption();
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=1, dataProvider="validCredentialsSupplier")
	public void verifyLoginwithValidCredentials(String email, String password) {
		
		loginPage.login(email, password);
		AccountPage accountPage = new AccountPage(driver);
		
		AssertJUnit.assertTrue(accountPage.VerifyLinkIsDisplayed());	
	}
	
	@Test(priority=2)
	public void verifyLoginInvalidUserInvalidPwd() {
		
		loginPage.login("simron_" + generateTimestamp() + "@gmail.com", dataProp.getProperty("invalidPwd"));
		
		AssertJUnit.assertTrue(loginPage.warningMssgDisplayed());
	}
	
	@Test(priority=3)
	public void verifyLoginValidUserInvalidPwd() {
		

		loginPage.login(dataProp.getProperty("validEmail"), dataProp.getProperty("invalidPwd"));
		AssertJUnit.assertTrue(loginPage.warningMssgDisplayed());
	}
	
	@Test(priority=4)
	public void verifyLoginEmptyUserEmptyPwd() {
		
		loginPage.clickOnLoginButton();
		AssertJUnit.assertTrue(loginPage.warningMssgDisplayed());
	}
	
	public String generateTimestamp() {
		
		Date currentDate = new Date();
		return currentDate.toString().replace(" ", "_").replace(":","_");
	}
}
