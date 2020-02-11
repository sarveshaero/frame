package driver;

import java.lang.reflect.Method;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import methods.AppDependent;
import methods.AppIndependent;
import methods.Datatable;
import methods.ReportUtil;
import methods.TaskModule;
import methods.UserModule;

public class DriverScript {
	public static AppIndependent appInd = null;
	public static AppDependent appDep = null;
	public static TaskModule taskMethods = null;
	public static UserModule userMethods = null;
	public static Datatable datatable = null;
	public static String strResultLocation = null;
	public static String screenShotLocation = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static ReportUtil reports = null;
	public static String strModule = null;
	public static String strTestCaseID = null;
	public static String strController = null;
	
	
	@BeforeSuite
	public void loadClassFiles() {
		try {
			appInd = new AppIndependent();
			appDep = new AppDependent();
			datatable = new Datatable();
			userMethods = new UserModule();
			taskMethods = new TaskModule();
			reports = new ReportUtil();
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	@BeforeClass
	public void preRequisites() {
		try {
			strController = System.getProperty("user.dir")+"\\ExecutionController\\Controller.xlsx";
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	@Test
	public void executeTestScripts() {
		String strExecute = null;
		Class cls = null;
		Object obj = null;
		Method method = null;
		try {
			int pRows = datatable.getRowCount(strController, "Project");
			for(int i=0; i<pRows-1; i++)
			{
				strExecute = datatable.getCellData(strController, "Project", "ExecuteProject", i+1);
				if(strExecute.equalsIgnoreCase("Yes")) {
					String strProject = datatable.getCellData(strController, "Project", "ProjectName", i+1);
					
					int mRows = datatable.getRowCount(strController, strProject);
					for(int j=0; j<mRows-1;j++)
					{
						strExecute = datatable.getCellData(strController, strProject, "ExecuteModule", j+1);
						if(strExecute.equalsIgnoreCase("Yes")) {
							strModule = datatable.getCellData(strController, strProject, "ModuleName", j+1);
							int tcRows = datatable.getRowCount(strController, strModule);
							for(int k=0; k<tcRows-1; k++)
							{
								strExecute = datatable.getCellData(strController, strModule, "ExecuteTest", k+1);
								if(strExecute.equalsIgnoreCase("Yes")) {
									strTestCaseID = datatable.getCellData(strController, strModule, "TestCaseID", k+1);
									String strTestScript = datatable.getCellData(strController, strModule, "TestScriptName", k+1);
									String strClassName = datatable.getCellData(strController, strModule, "ClassName", k+1);
									
									cls = Class.forName(strClassName);
									obj = cls.newInstance();
									method = obj.getClass().getMethod(strTestScript);
									String status = String.valueOf(method.invoke(obj));
									if(status.equals("true")) {
										datatable.setCellData(strController, strModule, "Status", k+1, "PASSED");
									}else {
										datatable.setCellData(strController, strModule, "Status", k+1, "FAILED");
									}
								}
							}
						}
					}
				}
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	@AfterSuite
	public void postRequisite() {
		try {
			System.out.println("Pso-Requisite");
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	

}
