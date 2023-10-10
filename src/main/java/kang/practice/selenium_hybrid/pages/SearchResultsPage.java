package kang.practice.selenium_hybrid.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {
	
	WebDriver driver;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="iMac")
	private WebElement validProduct;
	
	@FindBy(css="#content > p:nth-child(7)")
	private WebElement errorText;
	
	public String getProductName() {
		return validProduct.getText();
	}
	
	public String getErrorMessageText() {
		return errorText.getText();
	}
}
