package commonFunction;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import util.AppUtil;
public class FunctionLibrary extends AppUtil{
	WebDriverWait mywait;
//method for login
	public void adminLogin(String user,String pass) throws Throwable
	{
		//wait until reset button is clickable
		mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		mywait.until(ExpectedConditions.elementToBeClickable(By.xpath(conpro.getProperty("Objreset"))));
		Thread.sleep(2000);
		driver.findElement(By.xpath(conpro.getProperty("Objreset"))).click();
		driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).clear();
		driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(pass);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		Thread.sleep(2000);
				
	}
	//method to verify dashboard is displayed
	public boolean isDashboardDisplayed()
	{
		if(driver.findElement(By.xpath(conpro.getProperty("Objdashboard"))).isDisplayed())
		{
			mywait.until(ExpectedConditions.elementToBeClickable(By.xpath(conpro.getProperty("ObjLogout"))));
			driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
			Reporter.log("Dashboard page Displayed",true);
			return true;
		}
		else
		{
			Reporter.log("Dashboard page Not Displayed",true);
			return false;
		}
	}
	//method invalid data capture error message
	public boolean isErrMsgDisplayed() throws Throwable
	{
		//Capture error message
		String Error_message = driver.findElement(By.xpath(conpro.getProperty("ObjErr"))).getText().toLowerCase();
		driver.findElement(By.xpath(conpro.getProperty("ObjOk"))).click();
		Thread.sleep(2000);
		if(Error_message.contains("Incorrect")|| Error_message.contains("empty"))
		{
			Reporter.log(Error_message,true);
			return true;
		}else
		{
			Reporter.log(Error_message,true);
			return false;
		}
	}
}
