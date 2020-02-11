package testScripts;

import org.openqa.selenium.WebDriver;

import driver.DriverScript;





public class UserModuleScripts extends DriverScript{
	/**********************************
	 * Script Name	: TS_LoginLogout
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
			reports.startTest("UserLogin", strTestCaseID);
			test = extent.startTest("launchBrowser: Launching browser");
			oDriver = appInd.launchBrowser(appInd.readPropData("Browser"));
			if(oDriver!=null) {
				strStatus+=String.valueOf(appDep.navigateURL(oDriver));
				strStatus+=String.valueOf(appDep.loginToApp(oDriver, "user_101"));
				strStatus+=String.valueOf(appDep.logoutFromApp(oDriver));
				strStatus+=String.valueOf(appInd.closeBrowser(oDriver));
				
				if(strStatus.contains("false")) {
					reports.writeResult(null, "Fail", "The test script 'TS_LoginLogout()' was failed.", "No");
					return false;
				}else {
					reports.writeResult(null, "Pass", "The test script 'TS_LoginLogout()' was Passed.", "No");
					return true;
				}
			}else {
				reports.writeResult(null, "Fail", "Failed to launch the browser", "No");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'TS_LoginLogout()' test script."+e.getMessage(),"Yes");
			return false;
		}
		finally {
			extent.endTest(test);
			extent.flush();
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
			reports.startTest("CreateDeleteUser", strTestCaseID);
			test = extent.startTest("launchBrowser: Launching browser");
			oDriver = appInd.launchBrowser(appInd.readPropData("Browser"));
			if(oDriver!=null) {
				strStatus+=String.valueOf(appDep.navigateURL(oDriver));
				strStatus+=String.valueOf(appDep.loginToApp(oDriver, "user_102"));
				String strUserName = appDep.createUser(oDriver, "user_102");
				strStatus+=String.valueOf(appDep.deleteUser(oDriver, strUserName));
				strStatus+=String.valueOf(appDep.logoutFromApp(oDriver));
				strStatus+=String.valueOf(appInd.closeBrowser(oDriver));
				
				if(strStatus.contains("false")) {
					reports.writeResult(null, "Fail", "The test script 'TS_CreateDeleteUser()' was failed.", "No");
					return false;
				}else {
					reports.writeResult(null, "Pass", "The test script 'TS_CreateDeleteUser()' was Passed.", "No");
					return true;
				}
			}else {
				reports.writeResult(null, "Fail", "Failed to launch the browser", "No");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(null, "Exception", "Exception while executing 'TS_CreateDeleteUser()' test script."+e.getMessage(),"No");
			return false;
		}finally {
			extent.endTest(test);
			extent.flush();
		}
	}
}

