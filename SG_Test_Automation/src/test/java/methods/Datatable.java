package methods;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import driver.DriverScript;

public class Datatable extends DriverScript{
	/*******************************************
	 * Method Name	:  getDataFromExcel()
	 * Purpose		:  
	 * Author		:  test1
	 * Date created	:
	 * Reviewer Name:
	 * Example		: HashMap<String,String> oData = getDataFromExcel()
	 * ******************************************
	 */
	public Map<String, String> getDataFromExcel(String strSheetName, String strLogicalName)
	{
		FileInputStream fin = null;
		Workbook wb = null;
		Sheet sh = null;
		Row row1 = null;
		Row row2 = null;
		Cell cell1 = null;
		Cell cell2 = null;
		String sKey = null;
		String sValue = null;
		int rowNum = 0;
		Map<String, String> oDataMap = null;
		try {
			oDataMap = new HashMap<String, String>();
			fin = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+strModule+".xlsx");
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(strSheetName);
			if(sh==null) {
				reports.writeResult(null, "Fail", "The sheetname '"+strSheetName+"' was not found in the testdata file", "No");
				return null;
			}
			
			//Find rownumber for the given logicalName
			int rows = sh.getPhysicalNumberOfRows();
			for(int r=0;r<rows;r++)
			{
				row1 = sh.getRow(r);
				cell1 = row1.getCell(0);
				if(cell1.getStringCellValue().trim().equalsIgnoreCase(strLogicalName))
				{
					rowNum = r;
					break;
				}
			}
			
			if(rowNum>0)
			{
				row1 = sh.getRow(0);
				row2 = sh.getRow(rowNum);
				for(int c=0;c<row1.getLastCellNum();c++)
				{
					cell1 = row1.getCell(c);
					sKey = cell1.getStringCellValue();
					cell2 = row2.getCell(c);
					if(cell2==null||cell2.getCellTypeEnum()==cell2.getCellTypeEnum().BLANK)
					{
						sValue = "";
					}else if(cell2.getCellTypeEnum()==cell2.getCellTypeEnum().BOOLEAN)
					{
						sValue = String.valueOf(cell2.getBooleanCellValue());
					}
					else if(cell2.getCellTypeEnum()==cell2.getCellTypeEnum().STRING)
					{
						sValue = cell2.getStringCellValue();
					}
					else if(cell2.getCellTypeEnum()==cell2.getCellTypeEnum().NUMERIC)
					{
						if(HSSFDateUtil.isCellDateFormatted(cell2))
						{
							double dt = cell2.getNumericCellValue();
							Calendar cal = Calendar.getInstance();
							cal.setTime(HSSFDateUtil.getJavaDate(dt));
							String day;
							String month;
							String year;
							if(cal.get(Calendar.DAY_OF_MONTH)<10)
							{
								day="0"+cal.get(Calendar.DAY_OF_MONTH);
							}else {
								day=String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
							}
							
							if((cal.get(Calendar.MONTH)+1)<10)
							{
								month="0"+(cal.get(Calendar.MONTH)+1);
							}else {
								month=String.valueOf(cal.get(Calendar.MONTH)+1);
							}
							
							year = String.valueOf(cal.get(Calendar.YEAR));
							
							sValue = day+"/"+month+"/"+year;
								
						}else {
							sValue = String.valueOf(cell2.getNumericCellValue());
						}
					}
					oDataMap.put(sKey, sValue);
				}
			}else {
				reports.writeResult(null, "Fail", "The logicalName '"+strLogicalName+"' doesnot exist in test data", "No");
				return null;
			}
			
			return oDataMap;
		}catch(Exception e)
		{
			reports.writeResult(null, "Fail", "Exception while executing 'getDataFromExcel()' method. "+e.getMessage(), "No");
			return null;
		}
		finally {
			try {
				fin.close();
				fin = null;
				cell1 = null;
				cell2 = null;
				row1 = null;
				row2 = null;
				sh = null;
				wb.close();
				wb = null;
			}catch(Exception e)
			{
				reports.writeResult(null, "Fail", "Exception while executing 'getDataFromExcel()' method. "+e.getMessage(), "No");
				return null;
			}
		}
	}
	
	
	/**********************************
	 * Method Name	: getRowCount
	 * Author		: tester1
	 * purpose		: 
	 * 
	 * ********************************
	 */
	public int getRowCount(String strFilePath, String sheetName) {
		FileInputStream fin = null;
		Workbook wb = null;
		Sheet sh = null;
		try {
			fin = new FileInputStream(strFilePath);
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(sheetName);
			if(sh==null) {
				reports.writeResult(null, "Fail", "Invalid sheet name '"+sheetName+"'. Please specify the correct sheet name", "No");
				return -1;
			}
			
			return sh.getPhysicalNumberOfRows();
		}catch(Exception e)
		{
			reports.writeResult(null, "Exception", "Exception while executing 'getRowCount()' method. "+e.getMessage(), "No");
			return -1;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				sh = null;
				wb.close();
				wb = null;
			}catch(Exception e)
			{
				reports.writeResult(null, "Exception", "Exception while executing 'getRowCount()' method. "+e.getMessage(), "No");
				return -1;
			}
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
			if(cell==null || cell.getCellTypeEnum()==CellType.BLANK)
			{
				strData = "";
			}else if(cell.getCellTypeEnum()==CellType.BOOLEAN)
			{
				strData = String.valueOf(cell.getBooleanCellValue());
			}else if(cell.getCellTypeEnum()==CellType.STRING)
			{
				strData = cell.getStringCellValue();
			}else if(cell.getCellTypeEnum()==CellType.NUMERIC)
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
			reports.writeResult(null, "Exception", "Exception while executing 'getCellData()' method. "+e.getMessage(), "No");
			return null;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				cell = null;
				row = null;
				sh = null;
				wb.close();
				wb = null;
			}catch(Exception e)
			{
				reports.writeResult(null, "Exception", "Exception while executing 'getCellData()' method. "+e.getMessage(), "No");
				return null;
			}
		}
	}
	
	
	
	/**********************************
	 * Method Name	: setCellData
	 * Author		: tester1
	 * purpose		: to write to the runner .xlsx files
	 * 
	 * ********************************
	 */
	public void setCellData(String strFilePath, String sheetName, String columnName, int rowNum, String strStatus)
	{
		FileInputStream fin = null;
		FileOutputStream fout = null;
		Workbook wb = null;
		Sheet sh = null;
		Row row = null;
		Cell cell = null;
		int colNum = 0;
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
			
			if(sh.getRow(rowNum)==null) {
				row = sh.getRow(rowNum);
			}
			
			if(row.getCell(colNum)==null) {
				cell = row.createCell(colNum);
			}
			
			cell.setCellValue(strStatus);
			fout = new FileOutputStream(strFilePath);
			wb.write(fout);
		}catch(Exception e)
		{
			reports.writeResult(null, "Exception", "Exception while executing 'setCellData()' method. "+e.getMessage(), "No");
		}
		finally
		{
			try {
				fout.flush();
				fout.close();
				fout = null;
				fin.close();
				fin = null;
				cell = null;
				row = null;
				sh = null;
				wb.close();
				wb = null;
			}catch(Exception e)
			{
				reports.writeResult(null, "Exception", "Exception while executing 'setCellData()' method. "+e.getMessage(), "No");
			}
		}
	}
}

