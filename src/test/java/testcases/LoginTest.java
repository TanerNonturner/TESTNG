package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.DashboardPageWithFactory;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigsReader;
@Listeners(testbase.Listener.class)
public class LoginTest extends CommonMethods {
	
	@Test(groups ="smoke", priority= 0)
	public void login() {
	
		setUp();
		
		LoginPage lp = new LoginPage();
		sendText(lp.user,ConfigsReader.getProperty("user"));
		wait(1);
		sendText(lp.password,ConfigsReader.getProperty("password"));
		wait(1);
		click(lp.loginButton);
		
		boolean welcomeDispalyed =driver.findElement(By.id("welcome")).isDisplayed();
		Assert.assertTrue(welcomeDispalyed);
	}
	
	@Test(groups = {"regression","smoke"} ,priority =1)
	public void emptyPasswordLogin() {
		
		setUp();
		wait(1);
		LoginPage lp = new LoginPage();
		DashboardPageWithFactory dp = new DashboardPageWithFactory();
		test.info("Enterin Validation Login Credentials");
		//login user name 
		sendText(lp.user, ConfigsReader.getProperty("user"));
		click(lp.loginButton);
		wait(2);
		test.info("Verifying Empty Password Login Error Message");
		String expectedMessage = "Password can not be empty";
		String actualMessage=driver.findElement(By.id("spanMessage")).getText();
		wait(1);
		Assert.assertEquals(expectedMessage, actualMessage,"Error message doesn't match");
	}
	
	//@Test(groups ="regression",priority =2)
	public void invalidPasswordLogin() {
		setUp();
		wait(1);
		LoginPage lp = new LoginPage();
		DashboardPageWithFactory dp = new DashboardPageWithFactory();
		test.info("Entering Validation Login Credentials");
		sendText(lp.user, ConfigsReader.getProperty("user"));
		sendText(lp.password,"1233456789");
		click(lp.loginButton);
		
		wait(2);
		
		test.info("Verifying Ýnvalid Password Login Error Message");
		String expectedMessage = "Invalid credentials";
		String actualMessage = driver.findElement(By.id("spanMessage")).getText();
				
		Assert.assertEquals(expectedMessage, actualMessage,"Error message doesn't match!!");
		
		
	}
	
		
		
}		
		


