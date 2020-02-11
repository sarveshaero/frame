package driver;

import methods.AppDependentMethods;
import methods.AppIndependentMethods;
import testScripts.TestScripts;

public class DriverScript {
	public static AppIndependentMethods appInd = null;
	public static AppDependentMethods appDep = null;
	public static TestScripts scripts = null;
	public static boolean blnRes = false;
	
	
	public static void main(String[] args) {
		appInd = new AppIndependentMethods();
		appDep = new AppDependentMethods();
		scripts = new TestScripts();
		
		
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
		
		blnRes =scripts.TS_CreateDeleteCustomer();
		if(blnRes) {
			System.out.println("TS_CreateDeleteCustomer() PASSED");
		}else {
			System.out.println("TS_CreateDeleteCustomer() FAILED");
		}
	}
}