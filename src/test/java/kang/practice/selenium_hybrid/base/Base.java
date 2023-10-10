package kang.practice.selenium_hybrid.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import kang.practice.selenium_hybrid.utils.Utilities;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public void loadPropertiesFile() {
		
		prop = new Properties();
		
		dataProp = new Properties();
		
		File propFile = new File("/Users/simronkang/eclipse-workspace/selenium_hybrid/src/main/java/kang/practice/selenium_hybrid/config/config.properties");
		File dataFile = new File("/Users/simronkang/eclipse-workspace/selenium_hybrid/src/main/java/kang/practice/selenium_hybrid/testdata/testdata.properties");
								
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			FileInputStream dis = new FileInputStream(dataFile);
			dataProp.load(dis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public WebDriver intitializeUrlinBrowser(String browserName) {
		
		if(browserName.equals("chrome")) {
			driver  = new ChromeDriver();
		}else if (browserName.equals("firefox")) {
			driver  = new FirefoxDriver();
		}else if (browserName.equals("edge")) {
			driver  = new EdgeDriver();
		}else if (browserName.equals("safari")) {
			driver  = new SafariDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));
		driver.get(prop.getProperty("url"));
		return driver;
	}
}
