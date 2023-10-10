package kang.practice.selenium_hybrid.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReports() throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		ExtentReports extentReport = new ExtentReports();
		
		File extentReportFile = new File("/Users/simronkang/eclipse-workspace/selenium_hybrid/test-output/ExtentReports/extentReport.html");
		
		ExtentSparkReporter spark = new ExtentSparkReporter(extentReportFile);
		
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Selenium Hybrid Automation Results Report");
		spark.config().setDocumentTitle("Automation Test Report");
		spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		extentReport.attachReporter(spark);
		
		Properties configProp = new Properties();
		
		File configPropFile = new File("/Users/simronkang/eclipse-workspace/selenium_hybrid/src/main/java/kang/practice/selenium_hybrid/config/config.properties");
		
		FileInputStream configFis = new FileInputStream(configPropFile);
		
		try {
			configProp.load(configFis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser", configProp.getProperty("browser"));
		extentReport.setSystemInfo("Operating system", System.getProperty("os.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReport.setSystemInfo("Username", System.getProperty("java.version"));
		
		return extentReport;
	}
}
