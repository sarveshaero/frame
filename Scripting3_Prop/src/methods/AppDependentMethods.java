package methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import driver.DriverScript;

public class AppDependentMethods extends DriverScript{
	/**********************************
	 * Method Name	: navigateURL
	 * Author		: tester1
	 * purpose		: to navigate the URL
	 * 
	 * ********************************
	 */
	public boolean navigateURL(WebDriver oDriver)
	{
		try {
			oDriver.navigate().to(appInd.readPropData("URL"));
			Thread.sleep(2000);
			if(appInd.compareValue(oDriver.getTitle(), "actiTIME - Login")) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'navigateURL()' method. "+e.getMessage());
			return false;
		}
	}
	
	
	/**********************************
	 * Method Name	: loginToApp
	 * Author		: tester1
	 * purpose		: to login to the application
	 * 
	 * ********************************
	 */
	public boolean loginToApp(WebDriver oDriver)
	{
		String strStatus = null;
		try {
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.id("username"), appInd.readPropData("UserName")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("pwd"), appInd.readPropData("Password")));
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.tagName("a")));
			Thread.sleep(4000);
			
			//handle the short cut window
			if(appInd.isElementPresent(oDriver, By.xpath("//div[@style='display: block;']")))
			{
				strStatus+=String.valueOf(appInd.clickObject(oDriver, By.id("gettingStartedShortcutsMenuCloseId")));
			}
			
			if(strStatus.contains("false")) {
				appInd.writeResult("Fail", "Failed to login to the application");
				return false;
			}else {
				appInd.writeResult("Pass", "Login to the application was successful");
				return true;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'loginToApp()' method. "+e.getMessage());
			return false;
		}
	}
	
	
	/**********************************
	 * Method Name	: logoutFromApp
	 * Author		: tester1
	 * purpose		: to logout from the application
	 * 
	 * ********************************
	 */
	public boolean logoutFromApp(WebDriver oDriver)
	{
		String strStatus = null;
		try {
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//a[@id='logoutLink']")));
			Thread.sleep(2000);
			strStatus+=String.valueOf(appInd.verifyText(oDriver, By.xpath("//td[@id='headerContainer']"), "Text", "Please identify yourself"));
			
			if(strStatus.contains("false")) {
				appInd.writeResult("Fail", "Failed to logout from the application");
				return false;
			}else {
				appInd.writeResult("Pass", "Logout from the application was successful");
				return true;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'logoutFromApp()' method. "+e.getMessage());
			return false;
		}
	}
	
	
	
	/**********************************
	 * Method Name	: createUser
	 * Author		: tester1
	 * purpose		: to create the new user
	 * 
	 * ********************************
	 */
	public boolean createUser(WebDriver oDriver)
	{
		String strStatus = null;
		try {
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//div[text()='USERS']")));
			Thread.sleep(2000);
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//div[text()='Add User']")));
			Thread.sleep(2000);
			
			//Enter user details
			String strUN = appInd.readPropData("LastName")+", "+appInd.readPropData("FirstName");
			System.setProperty("RT_UserName", strUN);
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("firstName"), appInd.readPropData("FirstName")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("lastName"), appInd.readPropData("LastName")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("email"), appInd.readPropData("Email")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("username"), appInd.readPropData("User_UserName")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("password"), appInd.readPropData("User_Password")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("passwordCopy"), appInd.readPropData("User_ReTypePassword")));
			
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//span[text()='Create User']")));
			Thread.sleep(2000);
			
			//Verify user is created?
			strStatus+=String.valueOf(appInd.isElementPresent(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+strUN+"'"+"]")));
			
			if(strStatus.contains("false")) {
				appInd.writeResult("Fail", "Failed to create the user");
				return false;
			}else {
				appInd.writeResult("Pass", "The user was creted successful");
				return true;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'createUser()' method. "+e.getMessage());
			return false;
		}
	}
	
	
	/**********************************
	 * Method Name	: deleteUser
	 * Author		: tester1
	 * purpose		: to delete the new user
	 * 
	 * ********************************
	 */
	public boolean deleteUser(WebDriver oDriver, String userName)
	{
		String strStatus = null;
		try {
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+userName+"'"+"]")));
			Thread.sleep(2000);
			
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//button[contains(text(),'Delete User')]")));
			Thread.sleep(2000);
			oDriver.switchTo().alert().accept();
			Thread.sleep(2000);
			
			
			//verify user deleted
			strStatus+=String.valueOf(appInd.isElementNotPresent(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+userName+"'"+"]")));
			
			if(strStatus.contains("false")) {
				appInd.writeResult("Fail", "Failed to delete the user");
				return false;
			}else {
				appInd.writeResult("Pass", "The user was deleted successful");
				return true;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'deleteUser()' method. "+e.getMessage());
			return false;
		}
	}
	/**********************************
	 * Method Name	: createCustomer
	 * Author		: tester1
	 * purpose		: to create new Customer
	 * 
	 * ********************************
	 */
	public boolean createCustomer(WebDriver oDriver) {
		String strStatus=null;
		try {
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//div[text()='TASKS']")));
			Thread.sleep(2000);
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//div[text()='Add New']")));
			Thread.sleep(2000);
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//div[@class='item createNewCustomer ellipsis']")));
			Thread.sleep(2000);
			
			//Enter Customer Details
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.xpath("//input[@class='inputFieldWithPlaceholder']"), appInd.readPropData("Customer_Name")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.xpath("//textarea[@class='inputFieldWithPlaceholder']"), appInd.readPropData("Customer_Diss")));
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//div[@class='greyButton withIcon commitBtn']")));
			Thread.sleep(2000);
			
			//Verify Customer has Created??
			strStatus+=String.valueOf(appInd.isElementNotPresent(oDriver, By.xpath("//div[text()='SG Classes Bangalore ']")));
			
			if(strStatus.contains("false")) {
				appInd.writeResult("Fail", "Failed to create the Customer");
				return false;
			}else {
				appInd.writeResult("Pass", "The Customer was creted successful");
				return true;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'createCustomer()' method "+e.getMessage());
			return false;
		}
	}
	
	/**********************************
	 * Method Name	: deleteCustomer
	 * Author		: tester1
	 * purpose		: to create new Customer
	 * 
	 * ********************************
	 */
	
	public boolean deleteCustomer(WebDriver oDriver) {
		String strStatus = null;
		try {
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//div[@class='editButton available']")));
			Thread.sleep(2000);
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//div[@class='actionButton']")));
			Thread.sleep(2000);
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//div[text()='Delete']")));
			Thread.sleep(2000);
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.id("customerPanel_deleteConfirm_submitTitle")));
			Thread.sleep(2000);
			
			//verify Customer deleted
			strStatus+=String.valueOf(appInd.isElementNotPresent(oDriver, By.xpath("//div[text()='SG Classes Bangalore ']")));
			if(strStatus.contains("false")) {
				appInd.writeResult("Fail", "Failed to delete the Customer");
				return false;
			}else {
				appInd.writeResult("Pass", "The Customer was deleted successful");
				return true;
			}
			
		}catch (Exception e) {
			appInd.writeResult("Exception", "Exception while executing 'deleteCustomer()' method "+e.getMessage());
			return false;
		}
	}	
}
