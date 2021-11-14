package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.AddEmployeePageElements;
import pages.DashboardPageWithFactory;
import pages.LoginPageWithFactory;
import pages.PersonelDetailsElements;
import utils.CommonMethods;
import utils.ConfigsReader;
import utils.ExcelUtility;
@Listeners(testbase.Listener.class)
public class AddEmployeTest  extends CommonMethods{
	
	//We don't put anything except Test Cases
	
	@BeforeMethod
	public void open() {
		setUp();
		
		
		LoginPageWithFactory login = new LoginPageWithFactory();
		sendText(login.user,ConfigsReader.getProperty("user"));
		sendText(login.password,ConfigsReader.getProperty("password"));
		click(login.loginButton);
	}
	
	@AfterMethod
	public void close() {
		
		tearDown();
	}
	
	@Test(dataProvider ="userExcelData")
	public void addEmployee(String firstname, String lastname, String username, String password) {
		
		wait(2);
	
		DashboardPageWithFactory dp = new DashboardPageWithFactory();
		AddEmployeePageElements addEmp = new AddEmployeePageElements();
		PersonelDetailsElements pd = new PersonelDetailsElements();
		wait(2);
		//login
		
		
		wait(5);
		//navigate to Add Employee Page
		jsClick(dp.PIM);
		jsClick(dp.addEmp);
		
		wait(2);
		
		//fill the name and lastname
		sendText(addEmp.firstName,firstname);
		sendText(addEmp.lastName,lastname);
		
		//to get empID
		String expectedEmployeeID = addEmp.employeeId.getAttribute("value");
		
		//show the Login Details
		jsClick(addEmp.checkBoxLoginDetails);
		
		wait(1);
		
		sendText(addEmp.userName,username);
		sendText(addEmp.password,password);
		sendText(addEmp.confirmPassword,password);
		jsClick(addEmp.saveButton);
		
		//verify new employee
		
		waitForVisibilty(pd.personelDetails);
		String actualEmployeeID = pd.employeeID.getAttribute("value");
		
		Assert.assertEquals(actualEmployeeID, expectedEmployeeID);	
		
			
	}
	
	@DataProvider(name ="userExcelData")
	public Object[][] getData(){
		//1 way
		//Object [][] data = ExcelUtility.excelIntroArray("C:\\Users\\P&B\\eclipse-workspace\\TestNGFramework\\src\\test\\resources\\testdata\\TestExcel.xls","Employee");
		
		//2 way one liner
		return ExcelUtility.excelIntroArray("C:\\Users\\P&B\\eclipse-workspace\\TestNGFramework\\src\\test\\resources\\testdata\\TestExcel.xlsx","Employee");
		
	}

	

}


