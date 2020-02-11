package methods;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import driver.DriverScript;


public class AppIndependent extends DriverScript {
	/**********************************
	 * Method Name	: getDateTime
	 * Author		: tester1
	 * purpose		: Date TimeStamp
	 * 
	 * ********************************
	 */
	public String getDateTime(String strDateFormat)
	{
		Date dt = null;
		SimpleDateFormat sdf = null;
		try {
			dt = new Date();
			sdf = new SimpleDateFormat(strDateFormat);
			return sdf.format(dt);
		}catch(Exception e)
		{
			return null;
		}
		finally
		{
			dt = null;
			sdf = null;
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
				reports.writeResult(oDriver, "Pass", "The element '"+String.valueOf(objBy)+"' was clicked successful", "No");
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+String.valueOf(objBy)+"' was not available in DOM", "Yes");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'clickObject()' method. "+e.getMessage(),"Yes");
			return false;
		}
		finally
		{
			oEle = null;
		}
	}
	
	
	
	/**********************************
	 * Method Name	: clickObject
	 * Author		: tester1
	 * purpose		: to click on the given element
	 * 
	 * ********************************
	 */
	public boolean clickObject(WebDriver oDriver, String objLocator)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(By.xpath(objLocator));
			if(oEle.isDisplayed()) {
				oEle.click();
				reports.writeResult(oDriver, "Pass", "The element '"+objLocator+"' was clicked successful", "No");
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+objLocator+"' was not available in DOM", "Yes");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'clickObject()' method. "+e.getMessage(),"Yes");
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
				reports.writeResult(oDriver, "Pass", "The value '"+strValue+"' was entered in the element '"+String.valueOf(objBy)+"' successful", "No");
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+String.valueOf(objBy)+"' was not available in DOM", "Yes");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'setObject()' method. "+e.getMessage(),"Yes");
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
	public boolean setObject(WebDriver oDriver, String objLocator, String strValue)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(By.xpath(objLocator));
			if(oEle.isDisplayed()) {
				oEle.sendKeys(strValue);
				reports.writeResult(oDriver, "Pass", "The value '"+strValue+"' was entered in the element '"+objLocator+"' successful", "No");
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+objLocator+"' was not available in DOM", "Yes");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'setObject()' method. "+e.getMessage(),"Yes");
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
				reports.writeResult(oDriver, "Pass", "The value '"+strValue+"' was selected from the element '"+String.valueOf(objBy)+"' successful", "No");
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+String.valueOf(objBy)+"' was not available in DOM", "Yes");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'selectObject()' method. "+e.getMessage(),"Yes");
			return false;
		}
		finally
		{
			oEle = null;
			oSel = null;
		}
	}
	
	
	/**********************************
	 * Method Name	: selectObject
	 * Author		: tester1
	 * purpose		: to select the value in the given dropdown element
	 * 
	 * ********************************
	 */
	public boolean selectObject(WebDriver oDriver, String objLocator, String strValue)
	{
		WebElement oEle = null;
		Select oSel = null;
		try {
			oEle = oDriver.findElement(By.xpath(objLocator));
			if(oEle.isDisplayed()) {
				oSel = new Select(oEle);
				oSel.selectByVisibleText(strValue);
				reports.writeResult(oDriver, "Pass", "The value '"+strValue+"' was selected from the element '"+objLocator+"' successful", "No");
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+objLocator+"' was not available in DOM", "Yes");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'selectObject()' method. "+e.getMessage(),"Yes");
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
	public boolean compareValue(WebDriver oDriver, String actual, String expected)
	{
		try {
			if(actual.equalsIgnoreCase(expected)) {
				reports.writeResult(oDriver, "Pass", "Both actual '"+actual+"' & expected '"+expected+"' values are matching", "No");
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "Mis-match in the both actual '"+actual+"' & expected '"+expected+"' values", "no");
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'compareValue()' method. "+e.getMessage(),"Yes");
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
						reports.writeResult(oDriver, "Fail", "Invalid object type '"+objType+"' was specified. please correct it..", "No");
				}
				
				if(appInd.compareValue(oDriver, actual, expected)) {
					return true;
				}else{
					return false;
				}
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+String.valueOf(objBy)+"' was not available in DOM", "Yes");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'verifyText()' method. "+e.getMessage(),"Yes");
			return false;
		}
		finally
		{
			oEle = null;
			oSel = null;
		}
	}
	
	
	/**********************************
	 * Method Name	: verifyText
	 * Author		: tester1
	 * purpose		: to validate the text present on the element
	 * 
	 * ********************************
	 */
	public boolean verifyText(WebDriver oDriver, String objLocator, String objType, String expected)
	{
		WebElement oEle = null;
		Select oSel = null;
		String actual = null;
		try {
			oEle = oDriver.findElement(By.xpath(objLocator));
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
						reports.writeResult(oDriver, "Fail", "Invalid object type '"+objType+"' was specified. please correct it..", "No");
				}
				
				if(appInd.compareValue(oDriver, actual, expected)) {
					return true;
				}else{
					return false;
				}
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+objLocator+"' was not available in DOM", "Yes");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'verifyText()' method. "+e.getMessage(),"Yes");
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
				reports.writeResult(oDriver, "Pass", "The element '"+String.valueOf(objBy)+"' was present in the DOM", "No");
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+String.valueOf(objBy)+"' was doesnot exist in the DOM", "Yes");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'isElementPresent()' method. "+e.getMessage(),"Yes");
			return false;
		}
	}
	
	
	
	/**********************************
	 * Method Name	: isElementPresent
	 * Author		: tester1
	 * purpose		: to verify present of the element
	 * 
	 * ********************************
	 */
	public boolean isElementPresent(WebDriver oDriver, String objLocator)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(By.xpath(objLocator));
			if(oEles.size()>0) {
				reports.writeResult(oDriver, "Pass", "The element '"+objLocator+"' was present in the DOM", "No");
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+objLocator+"' was doesnot exist in the DOM", "Yes");
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'isElementPresent()' method. "+e.getMessage(),"Yes");
			return false;
		}
	}
	
	
	
	/**********************************
	 * Method Name	: verifyOptionalElement
	 * Author		: tester1
	 * purpose		: to verify present of the element
	 * 
	 * ********************************
	 */
	public boolean verifyOptionalElement(WebDriver oDriver, String objLocator)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(By.xpath(objLocator));
			if(oEles.size()>0) {
				reports.writeResult(oDriver, "Info", "The element '"+objLocator+"' was present in the DOM", "Yes");
				return true;
			}else {
				reports.writeResult(oDriver, "Info", "The element '"+objLocator+"' was doesnot exist in the DOM", "Yes");
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'verifyOptionalElement()' method. "+e.getMessage(),"Yes");
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
				reports.writeResult(oDriver, "Fail", "The element '"+String.valueOf(objBy)+"' exist in the DOM", "No");
				return false;
			}else {
				reports.writeResult(oDriver, "Pass", "The element '"+String.valueOf(objBy)+"' doesnot present as expected", "Yes");
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'isElementNotPresent()' method. "+e.getMessage(),"Yes");
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
	public boolean isElementNotPresent(WebDriver oDriver, String objLocator)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(By.xpath(objLocator));
			if(oEles.size()>0) {
				reports.writeResult(oDriver, "Pass", "The element '"+objLocator+"' exist in the DOM", "No");
				return false;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+objLocator+"' doesnot present as expected", "Yes");
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'isElementNotPresent()' method. "+e.getMessage(),"Yes");
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
				case "chrome":
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Library\\drivers\\chromedriver.exe");
					oDriver = new ChromeDriver();
					break;
				case "firefox":
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
				reports.writeResult(oDriver, "Pass", "The '"+strBrowser+"' browser was launched successful", "No");
				return oDriver;
			}else {
				reports.writeResult(oDriver, "Fail", "Failed to launch the '"+strBrowser+"' browser was launched successful", "No");
				return null;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'launchBrowser()' method. "+e.getMessage(),"Yes");
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
			reports.writeResult(oDriver, "Exception", "Exception while executing 'closeBrowser()' method. "+e.getMessage(),"Yes");
			return false;
		}
	}
	
	
	/**********************************
	 * Method Name	: readPropData
	 * Author		: tester1
	 * purpose		: to read the testdata from .prop files
	 * 
	 * ********************************
	 */
	public String readPropData(String strKey)
	{
		FileInputStream fin = null;
		Properties prop = null;
		try {
			fin = new FileInputStream(System.getProperty("user.dir")+"\\Configuration\\Config.properties");
			prop = new Properties();
			prop.load(fin);
			
			return prop.getProperty(strKey);
		}catch(Exception e)
		{
			reports.writeResult(null, "Exception", "Exception while executing 'readPropData()' method. "+e.getMessage(), "No");
			return null;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				prop = null;
			}catch(Exception e)
			{
				reports.writeResult(null, "Exception", "Exception while executing 'readPropData()' method. "+e.getMessage(), "No");
				return null;
			}
		}
	}
	
}
