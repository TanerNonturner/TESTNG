package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonMethods;

public class DashboardPageWithFactory extends CommonMethods {

	@FindBy(xpath="//img[@alt='OrangeHRM']")
	public WebElement OrangeHRMlogo;
	
	@FindBy(id="welcome")
	public WebElement welcomeMessage;
	
	@FindBy(id="menu_pim_viewPimModule")
	public WebElement PIM;
	
	@FindBy(id="menu_pim_addEmployee")
	public WebElement addEmp;
	
	public DashboardPageWithFactory() {
		
		PageFactory.initElements(driver, this);
	}
}
