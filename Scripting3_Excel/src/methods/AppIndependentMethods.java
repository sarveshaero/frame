package methods;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import driver.DriverScript;

public class AppIndependentMethods extends DriverScript{
	/**********************************
	 * Method Name	: writeResult
	 * Author		: tester1
	 * purpose		: to write the results using log4j reports
	 * 
	 * ********************************
	 */
	public void writeResult(String status, String message)
	{
		Logger log = null;
		try {
			log = Logger.getLogger("Reports");
			switch(status.toLowerCase())
			{
				case "pass":
					log.info(message);
					break;
				case "fail":
					log.error(message);
					break;
				case "exception":
					log.fatal(message);
					break;
				default:
					System.out.println("Invalid status '"+status+"' was mentioned. please correct it...");
			}
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'writeResult()' method. "+e.getMessage());
		}
	}
	
	
	
	/**********************************
	 * Method Name	: clickObject
	 * Author		: tester1
	 * purpose		: to click on the given element
	 * 
	 * ********************************
	 */
	public boolean clickObject(WebDriver oDriver, By objBy)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed()) {
				oEle.click();
				appInd.writeResult("Pass", "The element '"+String.valueOf(objBy)+"' was clicked successful");
				return true;
			}else {
				appInd.writeResult("Fail", "The element '"+String.valueOf(objBy)+"' was not available in DOM");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'clickObject()' method. "+e.getMessage());
			return false;
		}
		finally
		{
			oEle = null;
		}
	}
	
	
	
	/**********************************
	 * Method Name	: setObject
	 * Author		: tester1
	 * purpose		: to enter the value in the given element
	 * 
	 * ********************************
	 */
	public boolean setObject(WebDriver oDriver, By objBy, String strValue)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed()) {
				oEle.sendKeys(strValue);
				appInd.writeResult("Pass", "The value '"+strValue+"' was entered in the element '"+String.valueOf(objBy)+"' successful");
				return true;
			}else {
				appInd.writeResult("Fail", "The element '"+String.valueOf(objBy)+"' was not available in DOM");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'setObject()' method. "+e.getMessage());
			return false;
		}
		finally
		{
			oEle = null;
		}
	}
	
	
	
	/**********************************
	 * Method Name	: selectObject
	 * Author		: tester1
	 * purpose		: to select the value in the given dropdown element
	 * 
	 * ********************************
	 */
	public boolean selectObject(WebDriver oDriver, By objBy, String strValue)
	{
		WebElement oEle = null;
		Select oSel = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed()) {
				oSel = new Select(oEle);
				oSel.selectByVisibleText(strValue);
				appInd.writeResult("Pass", "The value '"+strValue+"' was selected from the element '"+String.valueOf(objBy)+"' successful");
				return true;
			}else {
				appInd.writeResult("Fail", "The element '"+String.valueOf(objBy)+"' was not available in DOM");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'selectObject()' method. "+e.getMessage());
			return false;
		}
		finally
		{
			oEle = null;
			oSel = null;
		}
	}
	
	
	/**********************************
	 * Method Name	: compareValue
	 * Author		: tester1
	 * purpose		: to compare the values
	 * 
	 * ********************************
	 */
	public boolean compareValue(String actual, String expected)
	{
		try {
			if(actual.equalsIgnoreCase(expected)) {
				appInd.writeResult("Pass", "Both actual '"+actual+"' & expected '"+expected+"' values are matching");
				return true;
			}else {
				appInd.writeResult("Fail", "Mis-match in the both actual '"+actual+"' & expected '"+expected+"' values");
				return true;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'compareValue()' method. "+e.getMessage());
			return false;
		}
	}
	
	
	/**********************************
	 * Method Name	: verifyText
	 * Author		: tester1
	 * purpose		: to validate the text present on the element
	 * 
	 * ********************************
	 */
	public boolean verifyText(WebDriver oDriver, By objBy, String objType, String expected)
	{
		WebElement oEle = null;
		Select oSel = null;
		String actual = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed())
			{
				switch(objType.toLowerCase())
				{
					case "text":
						actual = oEle.getText();
						break;
					case "value":
						actual = oEle.getAttribute("value");
						break;
					case "list":
						oSel = new Select(oEle);
						actual = oSel.getFirstSelectedOption().getText();
						break;
					default:
						appInd.writeResult("Fail", "Invalid object type '"+objType+"' was specified. please correct it..");
				}
				
				if(appInd.compareValue(actual, expected)) {
					return true;
				}else{
					return false;
				}
			}else {
				appInd.writeResult("Fail", "The element '"+String.valueOf(objBy)+"' was not available in DOM");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'verifyText()' method. "+e.getMessage());
			return false;
		}
		finally
		{
			oEle = null;
			oSel = null;
		}
	}
	
	
	/**********************************
	 * Method Name	: isElementPresent
	 * Author		: tester1
	 * purpose		: to verify present of the element
	 * 
	 * ********************************
	 */
	public boolean isElementPresent(WebDriver oDriver, By objBy)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(objBy);
			if(oEles.size()>0) {
				appInd.writeResult("Pass", "The element '"+String.valueOf(objBy)+"' was present in the DOM");
				return true;
			}else {
				appInd.writeResult("Fail", "The element '"+String.valueOf(objBy)+"' was doesnot exist in the DOM");
				return false;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'isElementPresent()' method. "+e.getMessage());
			return false;
		}
	}
	
	
	/**********************************
	 * Method Name	: isElementNotPresent
	 * Author		: tester1
	 * purpose		: to verify non-present of the element
	 * 
	 * ********************************
	 */
	public boolean isElementNotPresent(WebDriver oDriver, By objBy)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(objBy);
			if(oEles.size()>0) {
				appInd.writeResult("Fail", "The element '"+String.valueOf(objBy)+"' exist in the DOM");
				return false;
			}else {
				appInd.writeResult("Pass", "The element '"+String.valueOf(objBy)+"' doesnot present as expected");
				return true;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'isElementNotPresent()' method. "+e.getMessage());
			return false;
		}
	}
	
	
	/**********************************
	 * Method Name	: launchBrowser
	 * Author		: tester1
	 * purpose		: to launch the required browser
	 * 
	 * ********************************
	 */
	public WebDriver launchBrowser(String strBrowser)
	{
		WebDriver oDriver = null;
		try {
			switch(strBrowser.toLowerCase())
			{
				case "ch":
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Library\\drivers\\chromedriver.exe");
					oDriver = new ChromeDriver();
					break;
				case "ff":
					System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Library\\drivers\\geckodriver.exe");
					oDriver = new FirefoxDriver();
					break;
				case "ie":
					System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\Library\\drivers\\IEDriverServer.exe");
					oDriver = new InternetExplorerDriver();
					break;
			}
			
			if(oDriver!=null) {
				oDriver.manage().window().maximize();
				appInd.writeResult("Pass", "The '"+strBrowser+"' browser was launched successful");
				return oDriver;
			}else {
				appInd.writeResult("Fail", "Failed to launch the '"+strBrowser+"' browser was launched successful");
				return null;
			}
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'launchBrowser()' method. "+e.getMessage());
			return null;
		}
	}
	
	
	
	/**********************************
	 * Method Name	: closeBrowser
	 * Author		: tester1
	 * purpose		: to close the required browser
	 * 
	 * ********************************
	 */
	public boolean closeBrowser(WebDriver oDriver)
	{
		try {
			oDriver.close();
			return true;
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'closeBrowser()' method. "+e.getMessage());
			return false;
		}
	}
	
	
	
	/**********************************
	 * Method Name	: getCellData
	 * Author		: tester1
	 * purpose		: to read the testdata from .xlsx files
	 * 
	 * ********************************
	 */
	public String getCellData(String strFilePath, String sheetName, String columnName, int rowNum)
	{
		FileInputStream fin = null;
		Workbook wb = null;
		Sheet sh = null;
		Row row = null;
		Cell cell = null;
		int colNum = 0;
		String strData = null;
		String sDay = null;
		String sMonth = null;
		String sYear = null;
		try {
			fin = new FileInputStream(strFilePath);
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(sheetName);
			
			//Find out column Number based on colun name
			row = sh.getRow(0);
			for(int col=0; col<row.getPhysicalNumberOfCells(); col++)
			{
				cell = row.getCell(col);
				if(cell.getStringCellValue().trim().equalsIgnoreCase(columnName))
				{
					colNum = col;
					break;
				}
			}
			
			
			row = sh.getRow(rowNum);
			cell = row.getCell(colNum);
			
			if(row.getCell(colNum)==null) {
				cell = row.createCell(colNum);
			}
			
			//Format the call data
			if(cell==null || cell.getCellType()==CellType.BLANK)
			{
				strData = "";
			}else if(cell.getCellType()==CellType.BOOLEAN)
			{
				strData = String.valueOf(cell.getBooleanCellValue());
			}else if(cell.getCellType()==CellType.STRING)
			{
				strData = cell.getStringCellValue();
			}else if(cell.getCellType()==CellType.NUMERIC)
			{
				if(HSSFDateUtil.isCellDateFormatted(cell)) {
					double dt = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(dt));
					
					//Prefix zero for day if its <10
					if(cal.get(Calendar.DAY_OF_MONTH)<10)
					{
						sDay = "0"+cal.get(Calendar.DAY_OF_MONTH);
					}else {
						sDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
					}
					
					//Prefix zero for month if its <10
					if((cal.get(Calendar.MONTH)+1)<10)
					{
						sMonth = "0"+(cal.get(Calendar.MONTH)+1);
					}else {
						sMonth = String.valueOf((cal.get(Calendar.MONTH)+1));
					}
					
					sYear = String.valueOf(cal.get(Calendar.YEAR));
					
					strData = sDay+"/"+sMonth+"/"+sYear;
				}else {
					strData = String.valueOf(cell.getNumericCellValue());
				}
			}
			
			return strData;
		}catch(Exception e)
		{
			appInd.writeResult("Exception", "Exception while executing 'getCellData()' method. "+e.getMessage());
			return null;
		}
		finally
		{
			try {
				
			}catch(Exception e)
			{
				appInd.writeResult("Exception", "Exception while executing 'getCellData()' method. "+e.getMessage());
				return null;
			}
		}
	}
	
}
