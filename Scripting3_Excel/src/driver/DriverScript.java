package driver;

import methods.AppDependentMethods;
import methods.AppIndependentMethods;
import testScripts.TestScripts;

public class DriverScript {
	public static AppIndependentMethods appInd = null;
	public static AppDependentMethods appDep = null;
	public static TestScripts scripts = null;
	public static boolean blnRes = false;
	public static String strFilePath = null;
	
	
	public static void main(String[] args) {
		appInd = new AppIndependentMethods();
		appDep = new AppDependentMethods();
		scripts = new TestScripts();
		strFilePath = System.getProperty("user.dir")+"\\TestData\\TestData.xlsx";
		
		
		blnRes = scripts.TS_LoginLogout();
		if(blnRes) {
			System.out.println("TS_LoginLogout() PASSED");
		}else {
			System.out.println("TS_LoginLogout() FAILED");
		}
		System.out.println("************************************");
		
		blnRes = scripts.TS_CreateDeleteUser();
		if(blnRes) {
			System.out.println("TS_CreateDeleteUser() PASSED");
		}else {
			System.out.println("TS_CreateDeleteUser() FAILED");
		}
	}
}