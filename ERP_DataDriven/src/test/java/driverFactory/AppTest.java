package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunction.FunctionLibrary;
import util.AppUtil;
import util.ExcelFileUtil;

public class AppTest extends AppUtil{
String inputpath ="./FileInput/TestData.xlsx";
String Outputpath ="./FileOutPut/ValidaDataResults.xlsx";
String Outputpath1 ="./FileOutPut/InValidaDataResults.xlsx";
String TCSheet ="Login_ValidData";
String TCSheet1 ="Login_InvalidData";
FunctionLibrary lp;
@Test(enabled = true,priority = 0)
public void login_Valid() throws Throwable
{
	//create reference object for ExcelFileutil class
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows in login sheet
	int rc = xl.rowCount(TCSheet);
	Reporter.log("No of rows are::"+rc,true);
	//iterate all rows
	for(int i=1;i<=rc;i++)
	{
		String username = xl.getCellData(TCSheet, i, 0);
		String password = xl.getCellData(TCSheet, i, 1);
		//call admin login methos from Functionlibary
		 lp = new FunctionLibrary();
		lp.adminLogin(username, password);
		boolean res =lp.isDashboardDisplayed();
		if(res)
		{
		//write as pass into status cell
			xl.setCellData(TCSheet, i, 2, "Pass", Outputpath);
		}
		else
		{
			//write as Fail into status cell
			xl.setCellData(TCSheet, i, 2, "Fail", Outputpath);
		}
	}
	
}
@Test(enabled = true,priority = 1)
public void invalid_Login() throws Throwable
{
	//create reference object for ExcelFileutil class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count  no of rows 
		int rc =xl.rowCount(TCSheet1);
		Reporter.log("No of rows :::"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			String username = xl.getCellData(TCSheet1, i, 0);
			String password = xl.getCellData(TCSheet1, i, 1);
			lp= new FunctionLibrary();
			lp.adminLogin(username, password);
			boolean res =lp.isErrMsgDisplayed();
			if(res)
			{
				xl.setCellData(TCSheet1, i, 2, "Pass", Outputpath1);
			}
			else
			{
				xl.setCellData(TCSheet1, i, 2, "Fail", Outputpath1);
			}
		}
}
}







