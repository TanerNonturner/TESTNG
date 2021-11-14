package testbase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.ConfigsReader;
import utils.Constants;

public class BaseClass {
	
	public static WebDriver driver;
	
	public static ExtentHtmlReporter htmlReport;
	
	public static ExtentReports report;
	
	public static ExtentTest test;
	
	
	@BeforeTest(alwaysRun=true)
	public void generateReport() {
		// I must read Properties before to use it. in other words I must find the file (configuration.properties) and read it before to use
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		//report path in Constants class
		//initialize the report
		
		htmlReport = new ExtentHtmlReporter(Constants.REPORT_FILEPATH);
		
		htmlReport.config().setDocumentTitle(ConfigsReader.getProperty("reportTitle"));
		
		htmlReport.config().setReportName(ConfigsReader.getProperty("reportName"));
		//Background color Dark= black, standard = blue....
		htmlReport.config().setTheme(Theme.DARK);	
		
		report= new ExtentReports();
		report.attachReporter(htmlReport);	
	}
	
	@AfterTest(alwaysRun=true)
	public void writeReport() {
		
		report.flush();
	}
	
	
	
	/**
	 * This method creates a driver and returns it
	 * 
	 * @return WebDriver driver
	 */
	
	public static WebDriver setUp() {
		
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		
		
		switch(ConfigsReader.getProperty("browser").toLowerCase()) {
		case"chrome":
			System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRÝVER_FILEPATH);
			driver = new ChromeDriver();
			break;
		case"firefox":
			System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRÝVER_FILEPATH);
			driver= new FirefoxDriver();
			break;
		default:
			throw new RuntimeException("Browser not supported");
			
		}
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(ConfigsReader.getProperty("url"));
		
		
		
		return driver;
		
	}

	/**
	 * This method quits the browser.
	 * 
	 */
	
	public static void tearDown() {
		if(driver!=null) {
			driver.quit();
			
		}
		
		
	}
	
}
