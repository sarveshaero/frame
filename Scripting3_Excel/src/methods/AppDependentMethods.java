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
			oDriver.navigate().to(appInd.getCellData(strFilePath, "TestData", "URL", 1));
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
			String UN = appInd.getCellData(strFilePath, "TestData", "UN", 1);
			String PWD = appInd.getCellData(strFilePath, "TestData", "PWD", 1);
			
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.id("username"), UN));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("pwd"), PWD));
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
			String FN = appInd.getCellData(strFilePath, "TestData", "FN", 1);
			String LN = appInd.getCellData(strFilePath, "TestData", "LN", 1);
			String Email = appInd.getCellData(strFilePath, "TestData", "Email", 1);
			String UserName = appInd.getCellData(strFilePath, "TestData", "UserName", 1);
			String Password = appInd.getCellData(strFilePath, "TestData", "Password", 1);
			String Retype_PWD = appInd.getCellData(strFilePath, "TestData", "Retype_Pwd", 1);
			
			
			String strUN = LN+", "+FN;
			System.setProperty("RT_UserName", strUN);
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("firstName"), FN));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("lastName"), LN));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("email"), Email));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("username"), UserName));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("password"), Password));
			strStatus+=String.valueOf(appInd.setObject(oDriver, By.name("passwordCopy"), Retype_PWD));
			
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
}
