package kang.practice.selenium_hybrid.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import kang.practice.selenium_hybrid.base.Base;
import kang.practice.selenium_hybrid.pages.HomePage;
import kang.practice.selenium_hybrid.pages.SearchResultsPage;

public class SearchTest extends Base {
	
	public WebDriver driver;
	SearchResultsPage resultsPage;
	HomePage homePage = new HomePage(driver);
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@BeforeMethod
	public void sessionSetup() {
		loadPropertiesFile();
		driver = intitializeUrlinBrowser(prop.getProperty("browser"));
		homePage = new HomePage(driver);
	}
	
	@Test (priority=1)
	public void searchExistingItem() {
		homePage.enterValidProductName(dataProp.getProperty("validProduct"));
		resultsPage = homePage.clickSearchButton();
		AssertJUnit.assertEquals(resultsPage.getProductName(), "iMac");
	}
	
	@Test(priority=2)
	public void searchNonExistingItem() {
		homePage.enterValidProductName(dataProp.getProperty("invalidProduct"));
		resultsPage = homePage.clickSearchButton();
		AssertJUnit.assertEquals(resultsPage.getErrorMessageText(), dataProp.getProperty("unsuccessfulSearchWarning"));
	}
	
	@Test(priority=3)
	public void searchEmptyItem() {
		resultsPage = homePage.clickSearchButton();
		AssertJUnit.assertEquals(resultsPage.getErrorMessageText(), dataProp.getProperty("unsuccessfulSearchWarning"));
	}
}
