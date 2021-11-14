package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonMethods;

public class PersonelDetailsElements extends CommonMethods {
	
	
	@FindBy(xpath ="//*[@id=\"pdMainContainer\"]/div[1]/h1")
	public WebElement personelDetails;
	
	@FindBy(id="personal_txtEmployeeId")
	public WebElement employeeID;
	
	
	public PersonelDetailsElements() {
		
		PageFactory.initElements(driver, this);
	}
	
}
