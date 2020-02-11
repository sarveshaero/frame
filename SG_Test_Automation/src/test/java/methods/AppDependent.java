package methods;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import driver.DriverScript;
import locators.ObjectLocators;


public class AppDependent extends DriverScript implements ObjectLocators{
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
			if(appInd.compareValue(oDriver, oDriver.getTitle(), "actiTIME - Login")) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'navigateURL()' method. "+e.getMessage(), "Yes");
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
	public boolean loginToApp(WebDriver oDriver, String strLogicalName)
	{
		String strStatus = null;
		Map<String, String> objData = null;
		try {
			test = extent.startTest("loginToApp: Login to actiTime");
			objData = datatable.getDataFromExcel("loginToApp", strLogicalName);
					
			strStatus+=String.valueOf(appInd.setObject(oDriver, obj_User_UN_Edit, objData.get("UserName")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, obj_Password_Edit, objData.get("Password")));
			strStatus+=String.valueOf(appInd.clickObject(oDriver, obj_Login_Link));
			Thread.sleep(4000);
			
			//handle the short cut window
			if(appInd.verifyOptionalElement(oDriver, obj_ShortCut_Wnd))
			{
				strStatus+=String.valueOf(appInd.clickObject(oDriver, obj_ShortCut_Close_Btn));
			}
			
			if(strStatus.contains("false")) {
				reports.writeResult(oDriver, "Fail", "Failed to login to the application", "Yes");
				return false;
			}else {
				reports.writeResult(oDriver, "Pass", "Login to the application was successful", "No");
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'loginToApp()' method. "+e.getMessage(), "Yes");
			return false;
		}finally {
			extent.endTest(test);
			extent.flush();
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
			test = extent.startTest("logoutFromApp: Logout from actiTime");
			strStatus+=String.valueOf(appInd.clickObject(oDriver, obj_Logout_Link));
			Thread.sleep(2000);
			strStatus+=String.valueOf(appInd.verifyText(oDriver, obj_Header_Label, "Text", "Please identify yourself"));
			
			if(strStatus.contains("false")) {
				reports.writeResult(oDriver, "Fail", "Failed to logout from the application", "Yes");
				return false;
			}else {
				reports.writeResult(oDriver, "Pass", "Logout from the application was successful", "No");
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'logoutFromApp()' method. "+e.getMessage(), "Yes");
			return false;
		}finally {
			extent.endTest(test);
			extent.flush();
		}
	}
	
	
	
	/**********************************
	 * Method Name	: createUser
	 * Author		: tester1
	 * purpose		: to create the new user
	 * 
	 * ********************************
	 */
	public String createUser(WebDriver oDriver, String strLogicalName)
	{
		String strStatus = null;
		Map<String, String> objData = null;
		try {
			test = extent.startTest("createUser: Create new user");
			objData = datatable.getDataFromExcel("createUser", strLogicalName);
			
			strStatus+=String.valueOf(appInd.clickObject(oDriver, obj_Users_Menu));
			Thread.sleep(2000);
			strStatus+=String.valueOf(appInd.clickObject(oDriver, obj_AddUsers_Btn));
			Thread.sleep(2000);
			
			String strUN = objData.get("LastName")+", "+objData.get("FirstName");
			strStatus+=String.valueOf(appInd.setObject(oDriver, obj_FirstName_Edit, objData.get("FirstName")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, obj_LastName_Edit, objData.get("LastName")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, obj_Email_Edit, objData.get("Email")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, obj_User_UN_Edit, objData.get("U_UserName")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, obj_User_Pwd_Edit, objData.get("U_Password")));
			strStatus+=String.valueOf(appInd.setObject(oDriver, obj_User_RetypePwd_Edit, objData.get("RetypePwd")));
			
			strStatus+=String.valueOf(appInd.clickObject(oDriver, obj_CreateUser_Btn));
			Thread.sleep(2000);
			
			//Verify user is created?
			strStatus+=String.valueOf(appInd.isElementPresent(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+strUN+"'"+"]")));
			
			if(strStatus.contains("false")) {
				reports.writeResult(oDriver, "Fail", "Failed to create the user","Yes");
				return strUN;
			}else {
				reports.writeResult(oDriver, "Pass", "The user was creted successful", "No");
				return strUN;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'createUser()' method. "+e.getMessage(),"Yes");
			return null;
		}finally {
			extent.endTest(test);
			extent.flush();
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
			test = extent.startTest("deleteUser: Delet the user");
			strStatus+=String.valueOf(appInd.clickObject(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+userName+"'"+"]")));
			Thread.sleep(2000);
			
			strStatus+=String.valueOf(appInd.clickObject(oDriver, obj_DeleteUser_Btn));
			Thread.sleep(2000);
			oDriver.switchTo().alert().accept();
			Thread.sleep(2000);
			
			
			//verify user deleted
			strStatus+=String.valueOf(appInd.isElementNotPresent(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+userName+"'"+"]")));
			
			if(strStatus.contains("false")) {
				reports.writeResult(oDriver, "Fail", "Failed to delete the user", "Yes");
				return false;
			}else {
				reports.writeResult(oDriver, "Pass", "The user was deleted successful", "No");
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'deleteUser()' method. "+e.getMessage(),"Yes");
			return false;
		}finally {
			extent.endTest(test);
			extent.flush();
		}
	}
}


