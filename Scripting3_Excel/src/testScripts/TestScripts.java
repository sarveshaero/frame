package testScripts;

import org.openqa.selenium.WebDriver;

import driver.DriverScript;

public class TestScripts extends DriverScript{
	/**********************************
	 * Script Name	: navigateURL
	 * TestCase ID	: SRS_101
	 * Author		: tester1
	 * purpose		: 
	 * 
	 * ********************************
	 */
	public boolean TS_LoginLogout()
	{
		WebDriver oDriver = null;
		String strStatus = null;
		try {
			oDriver = appInd.launchBrowser(appInd.getCellData(strFilePath, "TestData", "Browser", 1));
			if(oDriver!=null) {
				strStatus+=String.valueOf(appDep.navigateURL(oDriver));
				strStatus+=String.valueOf(appDep.loginToApp(oDriver));
				strStatus+=String.valueOf(appDep.logoutFromApp(oDriver));
				strStatus+=String.valueOf(appInd.closeBrowser(oDriver));
				
				if(strStatus.contains("false")) {
					appInd.writeResult("Fail", "The test script 'TS_LoginLogout()' was failed.");
					return false;
				}else {
					appInd.writeResult("Pass", "The test script 'TS_LoginLogout()' was Passed.");
					return true;
				}
			}else {
				appInd.writeResult("Fail", "Failed to launch the browser");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'TS_LoginLogout()' test script."+e.getMessage());
			return false;
		}
	}
	
	
	
	/**********************************
	 * Script Name	: TS_CreateDeleteUser
	 * TestCase ID	: SRS_102
	 * Author		: tester1
	 * purpose		: 
	 * 
	 * ********************************
	 */
	public boolean TS_CreateDeleteUser()
	{
		WebDriver oDriver = null;
		String strStatus = null;
		try {
			oDriver = appInd.launchBrowser(appInd.getCellData(strFilePath, "TestData", "Browser", 1));
			if(oDriver!=null) {
				strStatus+=String.valueOf(appDep.navigateURL(oDriver));
				strStatus+=String.valueOf(appDep.loginToApp(oDriver));
				strStatus+=String.valueOf(appDep.createUser(oDriver));
				strStatus+=String.valueOf(appDep.deleteUser(oDriver, System.getProperty("RT_UserName")));
				strStatus+=String.valueOf(appDep.logoutFromApp(oDriver));
				strStatus+=String.valueOf(appInd.closeBrowser(oDriver));
				
				if(strStatus.contains("false")) {
					appInd.writeResult("Fail", "The test script 'TS_CreateDeleteUser()' was failed.");
					return false;
				}else {
					appInd.writeResult("Pass", "The test script 'TS_CreateDeleteUser()' was Passed.");
					return true;
				}
			}else {
				appInd.writeResult("Fail", "Failed to launch the browser");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'TS_CreateDeleteUser()' test script."+e.getMessage());
			return false;
		}
	}
}
